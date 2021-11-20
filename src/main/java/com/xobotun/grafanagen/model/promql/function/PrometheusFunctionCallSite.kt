package com.xobotun.grafanagen.model.promql.function

import com.xobotun.grafanagen.model.promql.datatype.DataType
import com.xobotun.grafanagen.model.promql.datatype.FloatLiteral
import com.xobotun.grafanagen.model.promql.datatype.InstantVector
import com.xobotun.grafanagen.model.promql.datatype.RangeVector
import kotlin.reflect.KClass

/**
 * Specifies metadata on function.
 * I.e. its name, input and output types.
 */
data class PrometheusFunctionCallSite(
    val metadata: PrometheusFunction,
    val argumentTypes: List<KClass<out DataType>>, // TODO: providers?
    val returnType: KClass<out DataType>,
)
