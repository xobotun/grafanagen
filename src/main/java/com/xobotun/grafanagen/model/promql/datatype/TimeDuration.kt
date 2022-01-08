package com.xobotun.grafanagen.model.promql.datatype

/**
 * Class for specifying time ranges and durations
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#time-durations
 */
data class TimeDuration(
    val milliseconds: Int? = null,
    val seconds: Int? = null,
    val minutes: Int? = null,
    val hours: Int? = null,
    val days: Int? = null,
    val weeks: Int? = null,
    val years: Int? = null,
    val isNegated: Boolean = false
): Literal, StringScalarProvider {
    override fun invoke() = StringScalar::class
}
