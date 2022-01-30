package com.xobotun.grafanagen.printer.promql.oneline

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider
import com.xobotun.grafanagen.model.promql.datatype.InstantVectorProvider
import com.xobotun.grafanagen.model.promql.function.PrometheusFunctionCall
import com.xobotun.grafanagen.model.promql.operator.*
import com.xobotun.grafanagen.printer.promql.Printer
import com.xobotun.grafanagen.printer.promql.wrapInBrackets

/**
 * An entrypoint to any PromQL printing code
 */
object OneLineCallsitePrinter : Printer<DataTypeProvider> {
    private val supportedSubtypes = setOf(
        PrometheusFunctionCall::class,
        PrometheusBiScalarOperatorCall::class,
        PrometheusScalarInstantVectorOperatorCall::class,
        PrometheusBiInstantVectorOperatorCall::class,
        PrometheusAggregateOperatorCall::class,
        PrometheusParametrizedAggregateOperatorCall::class,
    )

    override fun canPrint(entrypoint: DataTypeProvider): Boolean {
        return supportedSubtypes.contains(entrypoint::class)
    }

    override fun print(entrypoint: DataTypeProvider, printers: List<out Printer<DataTypeProvider>>): String {
        return when(entrypoint) {
            // Recursive printers
            is PrometheusFunctionCall -> FunctionPrinter.print(entrypoint, printers).wrapInBrackets()
            is PrometheusBiScalarOperatorCall -> BiScalarOperatorCall.print(entrypoint, printers).wrapInBrackets()
            is PrometheusScalarInstantVectorOperatorCall -> ScalarInstantVectorOperatorCall.print(entrypoint, printers).wrapInBrackets()
            is PrometheusBiInstantVectorOperatorCall -> BiInstantVectorOperatorCall.print(entrypoint, printers).wrapInBrackets()
            is PrometheusAggregateOperatorCall -> AggregateOperatorCall.print(entrypoint, printers).wrapInBrackets()
            is PrometheusParametrizedAggregateOperatorCall -> ParametrizedAggregateOperatorCall.print(entrypoint, printers).wrapInBrackets()
            // Uncertain recursion terminator
            else -> Printer.print(entrypoint, printers)
        }
    }
}

internal object FunctionPrinter : Printer<PrometheusFunctionCall> {
    override fun print(entrypoint: PrometheusFunctionCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val functionName = entrypoint.metadata.name
        val arguments = entrypoint.arguments.map { Printer.print(it, printers) }
        val joinedArgs = arguments.joinToString(", ")
        return "$functionName($joinedArgs)"
    }
}

/**
 * Between-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object BiScalarOperatorCall : Printer<PrometheusBiScalarOperatorCall> {
    override fun print(entrypoint: PrometheusBiScalarOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val left = Printer.print(entrypoint.leftSide, printers)
        val right = Printer.print(entrypoint.rightSide, printers)
        return "$left ${entrypoint.metadata.sign} $right"
    }
}

/**
 * Between-vector-and-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object ScalarInstantVectorOperatorCall : Printer<PrometheusScalarInstantVectorOperatorCall> {
    override fun print(entrypoint: PrometheusScalarInstantVectorOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val left = Printer.print(entrypoint.leftSide, printers)
        val right = Printer.print(entrypoint.rightSide, printers)
        val sign = if (entrypoint.yieldBoolean) "${entrypoint.operator.sign} bool" else entrypoint.operator.sign

        return if (entrypoint.isFlipped) "$right $sign $left" else "$left $sign $right"
    }
}

/**
 * Between-vector mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators with
 * https://prometheus.io/docs/prometheus/2.23/querying/operators/#many-to-one-and-one-to-many-vector-matches support
 */
internal object BiInstantVectorOperatorCall : Printer<PrometheusBiInstantVectorOperatorCall> {
    override fun print(entrypoint: PrometheusBiInstantVectorOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val left = Printer.print(entrypoint.leftSide, printers)
        val right = Printer.print(entrypoint.rightSide, printers)
        val sign = if (entrypoint.yieldBoolean) "${entrypoint.operator.sign} bool" else entrypoint.operator.sign

        // TODO: Created verifying logic in the separate visitor hierarchy:
        //  • Zero string-providers is silly here
        //  • Some operators do not support grouping
        val matcher = entrypoint.labelMatcher?.let {
            val args = it.labels.map { Printer.print(it, printers) }
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        val grouper = entrypoint.labelGrouper?.let {
            val args = it.labels.map { Printer.print(it, printers) }
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }

        return listOfNotNull(left, sign, matcher, grouper, right).joinToString(" ")
    }
}

/**
 * Most of https://prometheus.io/docs/prometheus/2.23/querying/operators/#aggregation-operators
 */
internal object AggregateOperatorCall : Printer<PrometheusAggregateOperatorCall> {
    override fun print(entrypoint: PrometheusAggregateOperatorCall, printers: List<out Printer<in DataTypeProvider>>) =
        anyAggregateOperatorCall(
            printers,
            entrypoint.operator.name,
            null,
            entrypoint.vector,
            entrypoint.labelAggregator
        )
}

/**
 * Some of https://prometheus.io/docs/prometheus/2.23/querying/operators/#aggregation-operators
 */
internal object ParametrizedAggregateOperatorCall : Printer<PrometheusParametrizedAggregateOperatorCall> {
    override fun print(entrypoint: PrometheusParametrizedAggregateOperatorCall, printers: List<out Printer<in DataTypeProvider>>) =
    anyAggregateOperatorCall(
        printers,
        entrypoint.operator.name,
        entrypoint.parameter,
        entrypoint.vector,
        entrypoint.labelAggregator
    )
}

fun anyAggregateOperatorCall(
    printers: List<out Printer<in DataTypeProvider>>,
    operator: String,
    optionalParameter: DataTypeProvider?,
    vector: InstantVectorProvider,
    labelAggregator: PrometheusAggregateOperatorCall.LabelAggregator? = null
): String {
    val parameter = optionalParameter?. let { Printer.print(optionalParameter, printers) }
    val argument = Printer.print(vector, printers)
    val aggregator = labelAggregator?.let {
        val args = it.labels.map { Printer.print(it, printers) }
        val joinedArgs = args.joinToString()
        return@let "${it.type.keyword}($joinedArgs)"
    }

    val arguments = listOfNotNull(parameter, argument).joinToString()
    return if (aggregator == null) "$operator($arguments)" else "$operator $aggregator ($arguments)"
}
