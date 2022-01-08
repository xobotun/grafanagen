package com.xobotun.grafanagen.model.promql.datatype

/**
 * String-based things, like label names and regexes.
 * https://prometheus.io/docs/prometheus/2.23/querying/basics/#string-literals
 */
data class StringScalar(
    val value: String
) : DataType, StringScalarProvider, Literal {
    override fun invoke() = StringScalar::class
}

fun String.toScalar() = StringScalar(this)
