package com.xobotun.grafanagen.model.promql.datatype

/**
 * Data series at a specific instant.
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#instant-vector-selectors and
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#offset-modifier and
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#modifier
 */
data class InstantVector(
    val metricName: String,
    val labelMatchers: List<LabelMatcher>? = null,
    val offsetModifier: TimeDuration? = null,
    /** Unix timestamp or `start()` or `end()`. */
    val atModifier: String? = null,
) : DataType, RangeVectorProvider, Literal {
    override fun print(): String {
        val labels = labelMatchers?.joinToString { "${it.labelName} ${it.operator} ${it.value}" }?.let { "{$it}" } ?: ""
        return listOfNotNull("$metricName$labels", offsetModifier?.value, atModifier).joinToString(" ")
    }
    override fun invoke() = RangeVector::class

    data class LabelMatcher(
        val labelName: String,
        val operator: LabelComparisonOperator,
        val value: String,
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
