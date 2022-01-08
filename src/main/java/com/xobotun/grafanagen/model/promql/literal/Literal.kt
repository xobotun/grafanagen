package com.xobotun.grafanagen.model.promql.datatype

/**
 * An interface to allow literal values to be responsible for their representation.
 * Assumes there is a default representation allowed per scalar type.
 * Also may provide various handles to alter the string representation.
 *
 * https://prometheus.io/docs/prometheus/2.23/querying/basics/#expression-language-data-types
 */
interface Literal {
    fun print(): String
}
