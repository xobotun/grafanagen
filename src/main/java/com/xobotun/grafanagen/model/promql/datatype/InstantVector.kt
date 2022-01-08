package com.xobotun.grafanagen.model.promql.datatype

/**
 * Data series at a specific instant.
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#instant-vector-selectors and
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#offset-modifier and
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#modifier
 */
data class InstantVector(
    val metricName: StringScalarProvider,
    val labelMatchers: List<LabelMatcher>? = null,
    val offsetModifier: TimeDuration? = null,
    /** Unix timestamp or `start()` or `end()`. */
    val atModifier: StringScalarProvider? = null,
) : DataType, InstantVectorProvider, Literal {
    override fun invoke() = InstantVector::class

    data class LabelMatcher(
        val labelName: StringScalarProvider,
        val operator: LabelComparisonOperator,
        val value: StringScalarProvider,
    )

    data class LabelComparisonOperator internal constructor(
        val operator: String
    ) {
        companion object {
            val EQUAL = LabelComparisonOperator("=")
            val NOT_EQUAL = LabelComparisonOperator("!=")
            val EQUAL_REGEX = LabelComparisonOperator("=~")
            val NOT_EQUAL_REGEX = LabelComparisonOperator("!~")
        }
    }
}
