package com.xobotun.grafanagen.model.promql.datatype

/**
 * String-based things, like label names and regexes.
 */
data class StringScalar(
    val value: String
) : DataType

fun String.toScalar() = StringScalar(this)
