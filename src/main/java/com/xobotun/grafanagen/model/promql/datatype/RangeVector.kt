package com.xobotun.grafanagen.model.promql.datatype

/**
 * Data series over range (many instants).
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#range-vector-selectors
 */
data class RangeVector(
    val value: InstantVector,
    val timeDuration: TimeDuration,
) : DataType, RangeVectorProvider, Literal {
    override fun print(): String {
        val labels = value.labelMatchers?.joinToString { "${it.labelName} ${it.operator} ${it.value}" }?.let { "{$it}" } ?: ""
        return listOfNotNull("${value.metricName}$labels[${timeDuration.value}]", value.offsetModifier?.value, value.atModifier).joinToString(" ")
    }
    override fun invoke() = RangeVector::class
}
