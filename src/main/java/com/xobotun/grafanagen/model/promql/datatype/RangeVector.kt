package com.xobotun.grafanagen.model.promql.datatype

/**
 * Data series over range (many instants).
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#range-vector-selectors
 */
data class RangeVector(
    val value: InstantVector,
    val timeDuration: TimeDuration,
) : DataType, RangeVectorProvider, Literal {
    override fun invoke() = RangeVector::class
}
