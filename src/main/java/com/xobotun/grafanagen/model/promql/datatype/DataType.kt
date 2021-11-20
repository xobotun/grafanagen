package com.xobotun.grafanagen.model.promql.datatype

import kotlin.reflect.KClass

/**
 * Marker interface enumerating possible data types in PromQL
 */
sealed interface DataType

typealias DataTypeProvider = () -> KClass<out DataType>
