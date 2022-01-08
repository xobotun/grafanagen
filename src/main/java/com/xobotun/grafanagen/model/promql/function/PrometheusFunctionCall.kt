package com.xobotun.grafanagen.model.promql.function

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider

/**
 * Specifies function call site.
 * I.e. its type and arguments.
 */
data class PrometheusFunctionCall(
    val metadata: PrometheusFunction,
    val arguments: List<DataTypeProvider>, // TODO: compile-time verification? // TODO: create a verification visitor hierearchy.
) : DataTypeProvider {
    override fun invoke() = metadata.returnType
}
