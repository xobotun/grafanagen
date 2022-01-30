package com.xobotun.grafanagen.printer.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.*
import com.xobotun.grafanagen.model.promql.function.PrometheusFunctionCall
import com.xobotun.grafanagen.model.promql.literal.PrometheusFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.SimpleFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.StringLiteral
import com.xobotun.grafanagen.model.promql.operator.*
import com.xobotun.grafanagen.printer.promql.Printer
import com.xobotun.grafanagen.printer.promql.wrapInBrackets
import java.lang.IllegalArgumentException

/**
 * Supports [OneLinePrinter] in printing complex literals.
 * Must not make recursive calls back to the caller.
 */
object SimpleLiteralPrinter : Printer<DataTypeProvider> {
    private val supportedSubtypes = setOf(
        FloatScalar::class,
        SimpleFloatLiteral::class,
        PrometheusFloatLiteral::class,
        StringScalar::class,
        StringLiteral::class,
        TimeDuration::class,
        InstantVector::class,
        RangeVector::class,
    )

    override fun canPrint(entrypoint: DataTypeProvider): Boolean {
        return supportedSubtypes.contains(entrypoint::class)
    }

    override fun print(entrypoint: DataTypeProvider, printers: List<out Printer<DataTypeProvider>>): String {
        return when(entrypoint) {
            // Simple cases
            is FloatScalar -> entrypoint.value.toString()
            is SimpleFloatLiteral -> entrypoint.let { "%.${it.precision}f".format(it.value) }
            is PrometheusFloatLiteral -> entrypoint.prometheusFloat
            is StringScalar -> entrypoint.value
            is StringLiteral -> entrypoint.let { s -> s.escapeSymbol?.let { "$it${s.value}$it" } ?: s.value }
            // Cases more difficult logic
            is TimeDuration -> TimeDurationPrinter.print(entrypoint, printers)
            is InstantVector -> InstantVectorPrinter.print(entrypoint, printers)
            is RangeVector -> RangeVectorPrinter.print(entrypoint, printers)
            else -> Printer.print(entrypoint, printers)
        }
    }
}

object TimeDurationPrinter : Printer<TimeDuration> {
    override fun print(entrypoint: TimeDuration, printers: List<Printer<in DataTypeProvider>>) = print(entrypoint)

    fun print(entrypoint: TimeDuration): String {
        val possbleMinus = if (entrypoint.isNegated) '-' else null
        val milliseconds = entrypoint.milliseconds?.let { "${it}ms" }
        val seconds =      entrypoint.seconds     ?.let { "${it}s" }
        val minutes =      entrypoint.minutes     ?.let { "${it}m" }
        val hours =        entrypoint.hours       ?.let { "${it}h" }
        val days =         entrypoint.days        ?.let { "${it}d" }
        val weeks =        entrypoint.weeks       ?.let { "${it}w" }
        val years =        entrypoint.years       ?.let { "${it}y" }
        return listOfNotNull(possbleMinus, years, weeks, days, hours, minutes, seconds, milliseconds).joinToString("")
    }
}

internal object InstantVectorPrinter : Printer<InstantVector> {
    override fun print(entrypoint: InstantVector, printers: List<Printer<in DataTypeProvider>>) = printAnyVector(
        printers,
        entrypoint.metricName,
        entrypoint.labelMatchers,
        entrypoint.offsetModifier,
        entrypoint.atModifier,
    )
}

internal object RangeVectorPrinter : Printer<RangeVector> {
    override fun print(entrypoint: RangeVector, printers: List<Printer<in DataTypeProvider>>) = printAnyVector(
        printers,
        entrypoint.value.metricName,
        entrypoint.value.labelMatchers,
        entrypoint.value.offsetModifier,
        entrypoint.value.atModifier,
        entrypoint.timeDuration
    )
}

internal fun printAnyVector(
    printers: List<Printer<in DataTypeProvider>>,
    metricName: StringScalarProvider,
    labelMatchers: List<InstantVector.LabelMatcher>? = null,
    offsetModifier: TimeDuration? = null,
    atModifier: StringScalarProvider? = null,
    timeDuration: TimeDuration? = null,
): String {
    val metric = Printer.print(metricName, printers)
    val labels = labelMatchers?.map {
        val label = Printer.print(it.labelName, printers)
        val expression = Printer.print(it.value, printers)

        return@map "$label${it.operator.operator}$expression"
    }
    val joinedLabels = labels?.joinToString()?.let { "{$it}" }
    val offset = offsetModifier?.let { "offset " + TimeDurationPrinter.print(it) }
    val at = atModifier?.let { "@ " + Printer.print(it, printers) }
    val scrapeRange = timeDuration?.let { TimeDurationPrinter.print(it) } ?.let { "[$it]" }

    val solidPart = listOfNotNull(metric, joinedLabels, scrapeRange).joinToString("")
    val rangedOrInstant = listOfNotNull(solidPart, offset, at).joinToString(" ")

    val needToWrap = offset != null || at != null // curly and square brackets are unnecessary to wrap. Having some spaces makes adding round brackets desirable, imho.
    return if (needToWrap) rangedOrInstant.wrapInBrackets() else rangedOrInstant
}
