package com.xobotun.grafanagen.printer.promql.oneline

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider
import com.xobotun.grafanagen.model.promql.datatype.Literal
import com.xobotun.grafanagen.model.promql.function.PrometheusFunctionCall
import com.xobotun.grafanagen.model.promql.operator.*
import com.xobotun.grafanagen.printer.promql.Printer
import com.xobotun.grafanagen.printer.promql.literal.SimpleLiteralPrinter
import java.lang.IllegalArgumentException

/**
 * An entrypoint to any PromQL printing code
 */
object OneLinePrinter : Printer<DataTypeProvider> {
    override fun print(entrypoint: DataTypeProvider): String {
        return when(entrypoint) {
            // Recursion terminator
            is Literal -> SimpleLiteralPrinter.print(entrypoint)
            // Recursive printers
            is PrometheusFunctionCall -> FunctionPrinter.print(entrypoint)
            is PrometheusBiScalarOperatorCall -> BiScalarOperatorCall.print(entrypoint)
            is PrometheusScalarInstantVectorOperatorCall -> ScalarInstantVectorOperatorCall.print(entrypoint)
            is PrometheusBiInstantVectorOperatorCall -> BiInstantVectorOperatorCall.print(entrypoint)
            is PrometheusAggregateOperatorCall -> AggregateOperatorCall.print(entrypoint)
            is PrometheusParametrizedAggregateOperatorCall -> ParametrizedAggregateOperatorCall.print(entrypoint)
            else -> throw IllegalArgumentException("Cannot print ${entrypoint::class}, no suitable handler! Entrypoint: $entrypoint")
        }
    }
}

internal object FunctionPrinter : Printer<PrometheusFunctionCall> {
    override fun print(entrypoint: PrometheusFunctionCall): String {
        val functionName = entrypoint.metadata.name
        val arguments = entrypoint.arguments.map(OneLinePrinter::print)
        val joinedArgs = arguments.joinToString(", ")
        return "$functionName($joinedArgs)"
    }
}

/**
 * Between-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object BiScalarOperatorCall : Printer<PrometheusBiScalarOperatorCall> {
    override fun print(entrypoint: PrometheusBiScalarOperatorCall): String {
        val left = OneLinePrinter.print(entrypoint.leftSide)
        val right = OneLinePrinter.print(entrypoint.rightSide)
        return "$left ${entrypoint.metadata.sign} $right"
    }
}

/**
 * Between-vector-and-scalar mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators
 */
internal object ScalarInstantVectorOperatorCall : Printer<PrometheusScalarInstantVectorOperatorCall> {
    override fun print(entrypoint: PrometheusScalarInstantVectorOperatorCall): String {
        val left = OneLinePrinter.print(entrypoint.leftSide)
        val right = OneLinePrinter.print(entrypoint.rightSide)
        val sign = if (entrypoint.yieldBoolean) "${entrypoint.operator.sign} bool" else entrypoint.operator.sign

        return if (entrypoint.isFlipped) "$right $sign $left" else "$left $sign $right"
    }
}

/**
 * Between-vector mode of https://prometheus.io/docs/prometheus/2.23/querying/operators/#binary-operators with
 * https://prometheus.io/docs/prometheus/2.23/querying/operators/#many-to-one-and-one-to-many-vector-matches support
 */
internal object BiInstantVectorOperatorCall : Printer<PrometheusBiInstantVectorOperatorCall> {
    override fun print(entrypoint: PrometheusBiInstantVectorOperatorCall): String {
        val left = OneLinePrinter.print(entrypoint.leftSide)
        val right = OneLinePrinter.print(entrypoint.rightSide)
        val sign = if (entrypoint.yieldBoolean) "${entrypoint.operator.sign} bool" else entrypoint.operator.sign

        // TODO: Created verifying logic in the separate visitor hierarchy:
        //  • Zero string-providers is silly here
        //  • Some operators do not support grouping
        val matcher = entrypoint.labelMatcher?.let {
            val args = it.labels.map(OneLinePrinter::print)
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        val grouper = entrypoint.labelGrouper?.let {
            val args = it.labels.map(OneLinePrinter::print)
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
    override fun print(entrypoint: PrometheusAggregateOperatorCall): String {
        val func = entrypoint.operator.name
        val argument = OneLinePrinter.print(entrypoint.vector)
        val aggregator = entrypoint.labelAggregator?.let {
            val args = it.labels.map(OneLinePrinter::print)
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
    override fun print(entrypoint: PrometheusParametrizedAggregateOperatorCall): String {
        val func = entrypoint.operator.name
        val parameter = OneLinePrinter.print(entrypoint.parameter)
        val argument = OneLinePrinter.print(entrypoint.vector)
        val aggregator = entrypoint.labelAggregator?.let {
            val args = it.labels.map(OneLinePrinter::print)
            val joinedArgs = args.joinToString()
            return@let "${it.type.keyword}($joinedArgs)"
        }
        return if (aggregator == null) "$func($parameter, $argument)" else "$func $aggregator ($parameter, $argument)"
    }
}
