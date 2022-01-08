package com.xobotun.grafanagen.model.promql.datatype

import kotlin.reflect.KClass

/**
 * Marker interface enumerating possible data types in PromQL.
 *
 * https://prometheus.io/docs/prometheus/2.23/querying/basics/#expression-language-data-types
 */
sealed interface DataType

typealias DataTypeProvider = () -> KClass<out DataType>
typealias FloatScalarProvider = () -> KClass<FloatScalar>
typealias StringScalarProvider = () -> KClass<StringScalar>
typealias InstantVectorProvider = () -> KClass<InstantVector>
typealias RangeVectorProvider = () -> KClass<RangeVector>
