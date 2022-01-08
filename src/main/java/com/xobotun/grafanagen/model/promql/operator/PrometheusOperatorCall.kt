package com.xobotun.grafanagen.model.promql.operator

import com.xobotun.grafanagen.model.promql.datatype.*
import kotlin.reflect.KClass


data class PrometheusBiScalarOperatorCall(
    val metadata: PrometheusBiScalarOperator,
    val leftSide: FloatScalarProvider,
    val rightSide: FloatScalarProvider
): FloatScalarProvider {
    override fun invoke() = FloatScalar::class
}

data class PrometheusScalarInstantVectorOperatorCall(
    val operator: PrometheusScalarInstantVectorOperator,
    /** In case we will need scalar to vector call */
    val isFlipped: Boolean,
    /** Whether `bool` modifier is provided and result will be a scalar */
    val yieldBoolean: Boolean = false,
    val leftSide: InstantVectorProvider,
    val rightSide: FloatScalarProvider,
): DataTypeProvider {
    override fun invoke(): KClass<out DataType> = if (operator.isBoolean && yieldBoolean) FloatScalar::class else InstantVector::class
}

data class PrometheusBiInstantVectorOperatorCall(
    val operator: PrometheusBiInstantVectorOperator,
    /** Whether `bool` modifier is provided and result will be a 0/1 vector */
    val yieldBoolean: Boolean = false,
    val leftSide: InstantVectorProvider,
    val rightSide: InstantVectorProvider,
    val labelMatcher: LabelMatcher? = null,
    val labelGrouper: LabelGrouper? = null,
): InstantVectorProvider {
    override fun invoke() = InstantVector::class

    data class LabelMatcher(
        val type: PrometheusBiInstantVectorOperator.LabelMatcherMode,
        val labels: List<StringScalarProvider>
    )

    data class LabelGrouper(
        val type: PrometheusBiInstantVectorOperator.LabelGrouperMode,
        val labels: List<StringScalarProvider>
    )
}

data class PrometheusAggregateOperatorCall(
    val operator: PrometheusAggregateOperator,
    val vector: InstantVectorProvider,
    val labelAggregator: LabelAggregator? = null,
): InstantVectorProvider {
    override fun invoke() = InstantVector::class

    data class LabelAggregator(
        val type: PrometheusAggregateOperator.LabelAggregateMode,
        val labels: List<StringScalarProvider>
    )
}

data class PrometheusParametrizedAggregateOperatorCall(
    val operator: PrometheusAggregateOperator,
    val vector: InstantVectorProvider,
    val parameter: DataTypeProvider,
    val labelAggregator: PrometheusAggregateOperatorCall.LabelAggregator? = null,
): InstantVectorProvider {
    override fun invoke() = InstantVector::class
}
