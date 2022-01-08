package com.xobotun.grafanagen.printer.promql.oneline

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider
import com.xobotun.grafanagen.model.promql.function.PrometheusFunctionCall
import com.xobotun.grafanagen.model.promql.operator.*
import com.xobotun.grafanagen.printer.promql.Printer

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
            is PrometheusFunctionCall -> FunctionPrinter.print(entrypoint, printers)
            is PrometheusBiScalarOperatorCall -> BiScalarOperatorCall.print(entrypoint, printers)
            is PrometheusScalarInstantVectorOperatorCall -> ScalarInstantVectorOperatorCall.print(entrypoint, printers)
            is PrometheusBiInstantVectorOperatorCall -> BiInstantVectorOperatorCall.print(entrypoint, printers)
            is PrometheusAggregateOperatorCall -> AggregateOperatorCall.print(entrypoint, printers)
            is PrometheusParametrizedAggregateOperatorCall -> ParametrizedAggregateOperatorCall.print(entrypoint, printers)
            // Uncertain recursion terminator
            else -> Printer.print(entrypoint, printers)
        }
    }
}

internal object FunctionPrinter : Printer<PrometheusFunctionCall> {
    override fun print(entrypoint: PrometheusFunctionCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val functionName = entrypoint.metadata.name
        val arguments = entrypoint.arguments.map { OneLineCallsitePrinter.print(it, printers) }
        val joinedArgs = arguments.joinToString(", ")
        return "$functionName($joinedArgs)"
    }
}

/**
 * Between-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object BiScalarOperatorCall : Printer<PrometheusBiScalarOperatorCall> {
    override fun print(entrypoint: PrometheusBiScalarOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val left = OneLineCallsitePrinter.print(entrypoint.leftSide, printers)
        val right = OneLineCallsitePrinter.print(entrypoint.rightSide, printers)
        return "$left ${entrypoint.metadata.sign} $right"
    }
}

/**
 * Between-vector-and-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object ScalarInstantVectorOperatorCall : Printer<PrometheusScalarInstantVectorOperatorCall> {
    override fun print(entrypoint: PrometheusScalarInstantVectorOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val left = OneLineCallsitePrinter.print(entrypoint.leftSide, printers)
        val right = OneLineCallsitePrinter.print(entrypoint.rightSide, printers)
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
        val left = OneLineCallsitePrinter.print(entrypoint.leftSide, printers)
        val right = OneLineCallsitePrinter.print(entrypoint.rightSide, printers)
        val sign = if (entrypoint.yieldBoolean) "${entrypoint.operator.sign} bool" else entrypoint.operator.sign

        // TODO: Created verifying logic in the separate visitor hierarchy:
        //  • Zero string-providers is silly here
        //  • Some operators do not support grouping
        val matcher = entrypoint.labelMatcher?.let {
            val args = it.labels.map { OneLineCallsitePrinter.print(it, printers) }
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        val grouper = entrypoint.labelGrouper?.let {
            val args = it.labels.map { OneLineCallsitePrinter.print(it, printers) }
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
    override fun print(entrypoint: PrometheusAggregateOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val func = entrypoint.operator.name
        val argument = OneLineCallsitePrinter.print(entrypoint.vector, printers)
        val aggregator = entrypoint.labelAggregator?.let {
            val args = it.labels.map { OneLineCallsitePrinter.print(it, printers) }
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        return if (aggregator == null) "$func($argument)" else "$func $aggregator ($argument)"
    }
}

/**
 * Some of https://prometheus.io/docs/prometheus/2.23/querying/operators/#aggregation-operators
 */
internal object ParametrizedAggregateOperatorCall : Printer<PrometheusParametrizedAggregateOperatorCall> {
    override fun print(entrypoint: PrometheusParametrizedAggregateOperatorCall, printers: List<out Printer<in DataTypeProvider>>): String {
        val func = entrypoint.operator.name
        val parameter = OneLineCallsitePrinter.print(entrypoint.parameter, printers)
        val argument = OneLineCallsitePrinter.print(entrypoint.vector, printers)
        val aggregator = entrypoint.labelAggregator?.let {
            val args = it.labels.map { OneLineCallsitePrinter.print(it, printers) }
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        return if (aggregator == null) "$func($parameter, $argument)" else "$func $aggregator ($parameter, $argument)"
    }
}
