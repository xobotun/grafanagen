package com.xobotun.grafanagen.model.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.DataType
import com.xobotun.grafanagen.model.promql.datatype.FloatScalar
import com.xobotun.grafanagen.model.promql.datatype.FloatScalarProvider
import com.xobotun.grafanagen.model.promql.datatype.Literal
import java.lang.IllegalArgumentException
import kotlin.math.round
import kotlin.reflect.KClass

/**
 * Same as [FloatScalar], but allows for limiting precision
 */
class SimpleFloatLiteral(
    val value: Double,
    val precision: Int
): FloatScalarProvider, Literal {
    override fun invoke() = FloatScalar::class
    override fun print() = "%.${precision}f".format(value)
}
