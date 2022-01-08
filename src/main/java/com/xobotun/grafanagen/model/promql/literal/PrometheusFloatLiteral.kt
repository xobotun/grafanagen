package com.xobotun.grafanagen.model.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.DataType
import com.xobotun.grafanagen.model.promql.datatype.FloatScalar
import com.xobotun.grafanagen.model.promql.datatype.FloatScalarProvider
import com.xobotun.grafanagen.model.promql.datatype.Literal
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

/**
 * A more sophisticated floating-point number representation from
 * https://prometheus.io/docs/prometheus/2.23/querying/basics/#float-literals
 */
class PrometheusFloatLiteral(
    val prometheusFloat: String
): FloatScalarProvider, Literal {
    init {
        if (!prometheusFloat.matches(regex)) throw IllegalArgumentException("$prometheusFloat is not matched under standard Prometheus float regex: `$regex`!")
    }

    override fun invoke() = FloatScalar::class

    companion object {
        val regex = "[-+]?([0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?|0[xX][0-9a-fA-F]+|[nN][aA][nN]|[iI][nN][fF])".toRegex()
    }
}
