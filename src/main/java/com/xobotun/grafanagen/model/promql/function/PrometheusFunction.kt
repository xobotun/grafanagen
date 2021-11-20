package com.xobotun.grafanagen.model.promql.function

import com.xobotun.grafanagen.model.promql.datatype.*
import kotlin.reflect.KClass

/**
 * Specifies metadata on function.
 * I.e. its name, input and output types.
 */
data class PrometheusFunction(
    val name: String,
    val argumentTypes: List<KClass<out DataType>>,
    val returnType: KClass<out DataType>,
)

////
/// Functions, as per https://prometheus.io/docs/prometheus/2.31/querying/functions/#functions
////

val ABS =                PrometheusFunction("abs", listOf(InstantVector::class), InstantVector::class)
val ABSENT =             PrometheusFunction("absent", listOf(InstantVector::class), InstantVector::class)
val ABSENT_OVER_TIME =   PrometheusFunction("absent_over_time", listOf(RangeVector::class), InstantVector::class)
val CEIL =               PrometheusFunction("ceil", listOf(InstantVector::class), FloatScalar::class)
val CHANGES =            PrometheusFunction("changes", listOf(RangeVector::class), FloatScalar::class)
val CLAMP =              PrometheusFunction("clamp", listOf(InstantVector::class, FloatScalar::class, FloatScalar::class), FloatScalar::class)
val CLAMP_MAX =          PrometheusFunction("clamp_max", listOf(InstantVector::class, FloatScalar::class), FloatScalar::class)
val CLAMP_MIN =          PrometheusFunction("clamp_min", listOf(InstantVector::class, FloatScalar::class), FloatScalar::class)
val DAY_OF_MONTH =       PrometheusFunction("day_of_month", listOf(InstantVector::class), FloatScalar::class)
val DAY_OF_MONTH_NOW =   PrometheusFunction("day_of_month", listOf(), FloatScalar::class)
val DAY_OF_WEEK =        PrometheusFunction("day_of_week", listOf(InstantVector::class), FloatScalar::class)
val DAY_OF_WEEK_NOW =    PrometheusFunction("day_of_week", listOf(), FloatScalar::class)
val DAYS_IN_MONTH =      PrometheusFunction("days_in_month", listOf(InstantVector::class), FloatScalar::class)
val DAYS_IN_MONTH_NOW =  PrometheusFunction("days_in_month", listOf(), FloatScalar::class)
val DELTA =              PrometheusFunction("delta", listOf(RangeVector::class), FloatScalar::class)
val DERIV =              PrometheusFunction("deriv", listOf(RangeVector::class), FloatScalar::class) // TODO
val EXP =                PrometheusFunction("exp", listOf(InstantVector::class), FloatScalar::class)
val FLOOR =              PrometheusFunction("floor", listOf(InstantVector::class), FloatScalar::class)
val HISTOGRAM_QUANTILE = PrometheusFunction("histogram_quantile", listOf(FloatScalar::class, InstantVector::class), FloatScalar::class) // TODO
val HOLT_WINTERS =       PrometheusFunction("holt_winters", listOf(RangeVector::class, FloatScalar::class, FloatScalar::class), FloatScalar::class)
val HOUR =               PrometheusFunction("hour", listOf(InstantVector::class), FloatScalar::class)
val HOUR_NOW =           PrometheusFunction("hour", listOf(), FloatScalar::class)
val IDELTA =             PrometheusFunction("idelta", listOf(RangeVector::class), FloatScalar::class)
val INCREASE =           PrometheusFunction("increase", listOf(RangeVector::class), FloatScalar::class)
val IRATE =              PrometheusFunction("irate", listOf(RangeVector::class), FloatScalar::class)
val LABEL_JOIN =         PrometheusFunction("label_join", listOf(InstantVector::class, StringScalar::class, StringScalar::class, /* This one should be a vararg*/ StringScalar::class), InstantVector::class) // TODO WTF vararg
val LABEL_REPLACE =      PrometheusFunction("label_replace", listOf(InstantVector::class, StringScalar::class, StringScalar::class, StringScalar::class, StringScalar::class), InstantVector::class) // TODO WTF regex
val LN =                 PrometheusFunction("ln", listOf(InstantVector::class), FloatScalar::class)
val LOG_2 =              PrometheusFunction("log2", listOf(InstantVector::class), FloatScalar::class)
val LOG_10 =             PrometheusFunction("log10", listOf(InstantVector::class), FloatScalar::class)
val MINUTE =             PrometheusFunction("minute", listOf(InstantVector::class), FloatScalar::class)
val MINUTE_NOW =         PrometheusFunction("minute", listOf(), FloatScalar::class)
val MONTH =              PrometheusFunction("month", listOf(InstantVector::class), FloatScalar::class)
val MONTH_NOW =          PrometheusFunction("month", listOf(), FloatScalar::class)
val PREDICT_LINEAR =     PrometheusFunction("predict_linear", listOf(RangeVector::class, FloatScalar::class), FloatScalar::class)
val RATE =               PrometheusFunction("rate", listOf(RangeVector::class), FloatScalar::class)
val RESETS =             PrometheusFunction("resets", listOf(RangeVector::class), FloatScalar::class)
val ROUND =              PrometheusFunction("round", listOf(InstantVector::class, FloatScalar::class), InstantVector::class) // TODO
val SCALAR =             PrometheusFunction("scalar", listOf(InstantVector::class), FloatScalar::class)
val SGN =                PrometheusFunction("sgn", listOf(InstantVector::class), InstantVector::class)
val SORT =               PrometheusFunction("sort", listOf(InstantVector::class), InstantVector::class)
val SORT_DESC =          PrometheusFunction("sort_desc", listOf(InstantVector::class), InstantVector::class)
val SQRT =               PrometheusFunction("sqrt", listOf(InstantVector::class), FloatScalar::class)
val TIME =               PrometheusFunction("time", listOf(), FloatScalar::class)
val TIMESTAMP =          PrometheusFunction("timestamp", listOf(InstantVector::class), FloatScalar::class)
val VECTOR =             PrometheusFunction("vector", listOf(FloatScalar::class), InstantVector::class)
val YEAR =               PrometheusFunction("year", listOf(InstantVector::class), FloatScalar::class)
val YEAR_NOW =           PrometheusFunction("year", listOf(), FloatScalar::class)

////
/// Aggregate functions, as per https://prometheus.io/docs/prometheus/2.31/querying/functions/#aggregation_over_time
////

val AVG_OVER_TIME =      PrometheusFunction("avg_over_time", listOf(RangeVector::class), FloatScalar::class)
val MIN_OVER_TIME =      PrometheusFunction("min_over_time", listOf(RangeVector::class), FloatScalar::class)
val MAX_OVER_TIME =      PrometheusFunction("max_over_time", listOf(RangeVector::class), FloatScalar::class)
val SUM_OVER_TIME =      PrometheusFunction("sum_over_time", listOf(RangeVector::class), FloatScalar::class)
val COUNT_OVER_TIME =    PrometheusFunction("count_over_time", listOf(RangeVector::class), FloatScalar::class)
val QUANTILE_OVER_TIME = PrometheusFunction("quantile_over_time", listOf(FloatScalar::class, RangeVector::class), FloatScalar::class)
val STDDEV_OVER_TIME =   PrometheusFunction("stddev_over_time", listOf(RangeVector::class), FloatScalar::class)
val STDVAR_OVER_TIME =   PrometheusFunction("stdvar_over_time", listOf(RangeVector::class), FloatScalar::class)
val LAST_OVER_TIME =     PrometheusFunction("last_over_time", listOf(RangeVector::class), FloatScalar::class)
val PRESENT_OVER_TIME =  PrometheusFunction("present_over_time", listOf(RangeVector::class), FloatScalar::class)

////
/// Trigonometric functions, as per https://prometheus.io/docs/prometheus/2.31/querying/functions/#trigonometric-functions
////

val ACOS =  PrometheusFunction("acos", listOf(InstantVector::class), FloatScalar::class)
val ACOSH = PrometheusFunction("acosh", listOf(InstantVector::class), FloatScalar::class)
val ASIN =  PrometheusFunction("asin", listOf(InstantVector::class), FloatScalar::class)
val ASINH = PrometheusFunction("asinh", listOf(InstantVector::class), FloatScalar::class)
val ATAN =  PrometheusFunction("atan", listOf(InstantVector::class), FloatScalar::class)
val ATANH = PrometheusFunction("atanh", listOf(InstantVector::class), FloatScalar::class)
val COS =   PrometheusFunction("cos", listOf(InstantVector::class), FloatScalar::class)
val COSH =  PrometheusFunction("cosh", listOf(InstantVector::class), FloatScalar::class)
val SIN =   PrometheusFunction("sin", listOf(InstantVector::class), FloatScalar::class)
val SINH =  PrometheusFunction("sinh", listOf(InstantVector::class), FloatScalar::class)
val TAN =   PrometheusFunction("tan", listOf(InstantVector::class), FloatScalar::class)
val TANH =  PrometheusFunction("tanh", listOf(InstantVector::class), FloatScalar::class)
val DEG =   PrometheusFunction("deg", listOf(InstantVector::class), FloatScalar::class)
val PI =    PrometheusFunction("pi", listOf(), FloatScalar::class)
val RAD =   PrometheusFunction("rad", listOf(InstantVector::class), FloatScalar::class)
