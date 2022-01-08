package com.xobotun.grafanagen.model.promql.datatype

import kotlin.reflect.KClass

/**
 * Any numerical value in Prometheus, including NaN.
 */
data class FloatScalar(
    val value: Double
) : DataType, FloatScalarProvider, Literal {
    override fun print() = value.toString()
    override fun invoke() = FloatScalar::class
}

fun Number.toScalar() = FloatScalar(this.toDouble())
