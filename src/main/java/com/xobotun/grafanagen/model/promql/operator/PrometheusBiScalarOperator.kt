package com.xobotun.grafanagen.model.promql.operator

import com.xobotun.grafanagen.model.promql.datatype.FloatScalar
import com.xobotun.grafanagen.model.promql.datatype.FloatScalarProvider
import kotlin.reflect.KClass

/**
 * Runs a comparison/arithmetic operation between two float scalars returning another scalar (0 or 1)
 * Boolean e.g.: `10 == bool 10` returns 1. `2 == bool 3` is 0, and `55 == 55` is invalid.
 * Arithmetic e.g.: `1 + 1` → 2.
 */
data class PrometheusBiScalarOperator internal constructor(
    val sign: String,
) {
    companion object {
        ////
        /// All from https://prometheus.io/docs/prometheus/latest/querying/operators/
        ////

        val ADDITION =         PrometheusBiScalarOperator("+")
        val SUBTRACTION =      PrometheusBiScalarOperator("-")
        val MULTIPLICATION =   PrometheusBiScalarOperator("*")
        val DIVISION =         PrometheusBiScalarOperator("/")
        val MODULO =           PrometheusBiScalarOperator("%")
        val EXPONENTIATION =   PrometheusBiScalarOperator("^")

        val EQUAL =            PrometheusBiScalarOperator("==")
        val NOT_EQUAL =        PrometheusBiScalarOperator("!=")
        val GREATER_THAN =     PrometheusBiScalarOperator(">")
        val LESS_THAN =        PrometheusBiScalarOperator("<")
        val GREATER_OR_EQUAL = PrometheusBiScalarOperator(">=")
        val LESS_OR_EQUAL =    PrometheusBiScalarOperator("<=")
    }
}


