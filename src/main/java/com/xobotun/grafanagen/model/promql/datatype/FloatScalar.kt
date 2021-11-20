package com.xobotun.grafanagen.model.promql.datatype

/**
 * Any numerical value in Prometheus, including NaN.
 */
data class FloatScalar(
    val value: Double
) : DataType

fun Number.toScalar() = FloatScalar(this.toDouble())
