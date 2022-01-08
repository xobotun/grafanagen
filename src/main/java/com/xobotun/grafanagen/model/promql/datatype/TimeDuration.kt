package com.xobotun.grafanagen.model.promql.datatype

/**
 * Class for specifying time ranges and durations
 * https://prometheus.io/docs/prometheus/latest/querying/basics/#time-durations
 */
data class TimeDuration(
    val value: String
): Literal, StringScalarProvider {
    init {
        if (!value.matches(regex) && value.length <= 1) throw IllegalArgumentException("$value is not matched under Prometheus time duration regex: `${regex}`!")
    }

    override fun invoke() = StringScalar::class
    override fun print() = value

    companion object {
        val regex = "-?(\\d+y)?(\\d+w)?(\\d+d)?(\\d+h)?(\\d+m)?(\\d+s)?(\\d+ms)?".toRegex()
    }
}
