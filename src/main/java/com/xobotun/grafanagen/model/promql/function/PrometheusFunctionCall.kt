package com.xobotun.grafanagen.model.promql.function

import com.xobotun.grafanagen.model.promql.datatype.DataType
import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider
import kotlin.reflect.KClass

/**
 * Specifies function call site.
 * I.e. its type and arguments.
 */
data class PrometheusFunctionCall(
    val metadata: PrometheusFunction,
    val arguments: List<DataTypeProvider>, // TODO: compile-time verification?
) : DataTypeProvider {
    override fun invoke() = metadata.returnType
}
