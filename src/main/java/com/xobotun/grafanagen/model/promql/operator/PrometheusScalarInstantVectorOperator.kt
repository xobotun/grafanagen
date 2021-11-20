package com.xobotun.grafanagen.model.promql.operator

import com.xobotun.grafanagen.model.promql.datatype.*
import kotlin.reflect.KClass

/**
 * Runs a comparison/arithmetic operation between a scalar and all instant vector values.
 * As I've found out there is no difference between scalar-to-vector and vector-to-scalar positioning.
 * Well, except for `1 > metric_name` yields same thing as `metric_name <= 1`. So this class has fixed positioning.
 *
 * Boolean → boolean e.g.: `metric_name > bool 30`
 * Boolean → instant vector e.g.: `metric_name > 30`
 * Arithmetic → instant vector e.g.: `metric_name * 30`
 */
data class PrometheusScalarInstantVectorOperator internal constructor(
    val sign: String,
    val isBoolean: Boolean = false
) {
    companion object {
        ////
        /// All from https://prometheus.io/docs/prometheus/latest/querying/operators/
        ////

        val ADDITION =         PrometheusScalarInstantVectorOperator("+")
        val SUBTRACTION =      PrometheusScalarInstantVectorOperator("-")
        val MULTIPLICATION =   PrometheusScalarInstantVectorOperator("*")
        val DIVISION =         PrometheusScalarInstantVectorOperator("/")
        val MODULO =           PrometheusScalarInstantVectorOperator("%")
        val EXPONENTIATION =   PrometheusScalarInstantVectorOperator("^")

        val EQUAL =            PrometheusScalarInstantVectorOperator("==", true)
        val NOT_EQUAL =        PrometheusScalarInstantVectorOperator("!=", true)
        val GREATER_THAN =     PrometheusScalarInstantVectorOperator(">",  true)
        val LESS_THAN =        PrometheusScalarInstantVectorOperator("<",  true)
        val GREATER_OR_EQUAL = PrometheusScalarInstantVectorOperator(">=", true)
        val LESS_OR_EQUAL =    PrometheusScalarInstantVectorOperator("<=", true)
    }
}


