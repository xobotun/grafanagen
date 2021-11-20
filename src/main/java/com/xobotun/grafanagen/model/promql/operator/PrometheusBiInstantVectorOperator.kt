package com.xobotun.grafanagen.model.promql.operator

import com.xobotun.grafanagen.model.promql.datatype.*
import kotlin.reflect.KClass

/**
 * Runs a comparison/arithmetic operation between two instant vector values.
 *
 * Boolean → instant vector e.g.: `metric_name > [bool] metric_name_2`
 * Arithmetic → instant vector e.g.: `metric_name * metric_name_2`
 */
data class PrometheusBiInstantVectorOperator internal constructor(
    val sign: String,
    val canUseGroupingModifier: Boolean = true
) {
    companion object {
        ////
        /// All from https://prometheus.io/docs/prometheus/latest/querying/operators/
        ////

        val ADDITION =         PrometheusBiInstantVectorOperator("+")
        val SUBTRACTION =      PrometheusBiInstantVectorOperator("-")
        val MULTIPLICATION =   PrometheusBiInstantVectorOperator("*")
        val DIVISION =         PrometheusBiInstantVectorOperator("/")
        val MODULO =           PrometheusBiInstantVectorOperator("%")
        val EXPONENTIATION =   PrometheusBiInstantVectorOperator("^")

        val ATAN_2 =           PrometheusBiInstantVectorOperator("atan2")

        val EQUAL =            PrometheusBiInstantVectorOperator("==")
        val NOT_EQUAL =        PrometheusBiInstantVectorOperator("!=")
        val GREATER_THAN =     PrometheusBiInstantVectorOperator(">")
        val LESS_THAN =        PrometheusBiInstantVectorOperator("<")
        val GREATER_OR_EQUAL = PrometheusBiInstantVectorOperator(">=")
        val LESS_OR_EQUAL =    PrometheusBiInstantVectorOperator("<=")

        val INTERSECTION =     PrometheusBiInstantVectorOperator("and",    false)
        val UNION =            PrometheusBiInstantVectorOperator("or",     false)
        val COMPLEMENT =       PrometheusBiInstantVectorOperator("unless", false)
    }

    data class LabelMatcherMode internal constructor(
        val keyword: String
    ) {
        companion object {
            val IGNORING = LabelMatcherMode("ignoring")
            val ON = LabelMatcherMode("on")
        }
    }

    data class LabelGrouperMode internal constructor(
        val keyword: String
    ) {
        companion object {
            val GROUP_LEFT = LabelMatcherMode("group_left")
            val GROUP_RIGHT = LabelMatcherMode("group_right")
        }
    }
}
