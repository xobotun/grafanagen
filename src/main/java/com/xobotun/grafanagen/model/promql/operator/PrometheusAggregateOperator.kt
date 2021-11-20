package com.xobotun.grafanagen.model.promql.operator

import com.xobotun.grafanagen.model.promql.datatype.*
import kotlin.reflect.KClass

/**
 * Runs an aggregate operation between over one instant vector values.
 */
data class PrometheusAggregateOperator internal constructor(
    val name: String,
) {
    companion object {
        ////
        /// All from https://prometheus.io/docs/prometheus/latest/querying/operators/
        ////

        val SUM =    PrometheusAggregateOperator("sum")
        val MIN =    PrometheusAggregateOperator("min")
        val MAX =    PrometheusAggregateOperator("max")
        val AVG =    PrometheusAggregateOperator("avg")
        val GROUP =  PrometheusAggregateOperator("group")
        val STDDEV = PrometheusAggregateOperator("stddev")
        val STDVAR = PrometheusAggregateOperator("stdvar")
        val COUNT =  PrometheusAggregateOperator("count")
    }

    data class LabelAggregateMode internal constructor(
        val keyword: String
    ) {
        companion object {
            val WITHOUT = LabelAggregateMode("without")
            val BY = LabelAggregateMode("by")
        }
    }
}

data class PrometheusParametrizedAggregateOperator internal constructor(
    val name: String,
    val parameterType: KClass<out DataType>
) {
    companion object {
        ////
        /// All from https://prometheus.io/docs/prometheus/latest/querying/operators/
        ////

        val COUNT_VALUES = PrometheusParametrizedAggregateOperator("count_values", StringScalar::class)
        val BOTTOMK =      PrometheusParametrizedAggregateOperator("bottomk",  FloatScalar::class)
        val TOPK =         PrometheusParametrizedAggregateOperator("topk",     FloatScalar::class)
        val QUANTILE =     PrometheusParametrizedAggregateOperator("quantile", FloatScalar::class)
    }
}
