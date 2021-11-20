package com.xobotun.grafanagen.model.promql.function

import com.xobotun.grafanagen.model.promql.datatype.DataType
import com.xobotun.grafanagen.model.promql.datatype.FloatLiteral
import com.xobotun.grafanagen.model.promql.datatype.InstantVector
import com.xobotun.grafanagen.model.promql.datatype.RangeVector
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
val CEIL =               PrometheusFunction("ceil", listOf(InstantVector::class), FloatLiteral::class)
val CHANGES =            PrometheusFunction("changes", listOf(RangeVector::class), FloatLiteral::class)
val CLAMP =              PrometheusFunction("clamp", listOf(InstantVector::class, FloatLiteral::class, FloatLiteral::class), FloatLiteral::class)
val CLAMP_MAX =          PrometheusFunction("clamp_max", listOf(InstantVector::class, FloatLiteral::class), FloatLiteral::class)
val CLAMP_MIN =          PrometheusFunction("clamp_min", listOf(InstantVector::class, FloatLiteral::class), FloatLiteral::class)
val DAY_OF_MONTH =       PrometheusFunction("day_of_month", listOf(InstantVector::class), FloatLiteral::class)
val DAY_OF_MONTH_NOW =   PrometheusFunction("day_of_month", listOf(), FloatLiteral::class)
val DAY_OF_WEEK =        PrometheusFunction("day_of_week", listOf(InstantVector::class), FloatLiteral::class)
val DAY_OF_WEEK_NOW =    PrometheusFunction("day_of_week", listOf(), FloatLiteral::class)
val DAYS_IN_MONTH =      PrometheusFunction("days_in_month", listOf(InstantVector::class), FloatLiteral::class)
val DAYS_IN_MONTH_NOW =  PrometheusFunction("days_in_month", listOf(), FloatLiteral::class)
val DELTA =              PrometheusFunction("delta", listOf(RangeVector::class), FloatLiteral::class)
val DERIV =              PrometheusFunction("deriv", listOf(RangeVector::class), FloatLiteral::class) // TODO
val EXP =                PrometheusFunction("exp", listOf(InstantVector::class), FloatLiteral::class)
val FLOOR =              PrometheusFunction("floor", listOf(InstantVector::class), FloatLiteral::class)
val HISTOGRAM_QUANTILE = PrometheusFunction("histogram_quantile", listOf(FloatLiteral::class, InstantVector::class), FloatLiteral::class) // TODO
val HOLT_WINTERS =       PrometheusFunction("holt_winters", listOf(RangeVector::class, FloatLiteral::class, FloatLiteral::class), FloatLiteral::class)
val HOUR =               PrometheusFunction("hour", listOf(InstantVector::class), FloatLiteral::class)
val HOUR_NOW =           PrometheusFunction("hour", listOf(), FloatLiteral::class)
val IDELTA =             PrometheusFunction("idelta", listOf(RangeVector::class), FloatLiteral::class)
val INCREASE =           PrometheusFunction("increase", listOf(RangeVector::class), FloatLiteral::class)
val IRATE =              PrometheusFunction("irate", listOf(RangeVector::class), FloatLiteral::class)
val LABEL_JOIN =         PrometheusFunction("label_join", ) // TODO WTF vararg
val LABEL_REPLACE =      PrometheusFunction("label_replace", ) // TODO WTF regex
val LN =                 PrometheusFunction("ln", listOf(InstantVector::class), FloatLiteral::class)
val LOG_2 =              PrometheusFunction("log2", listOf(InstantVector::class), FloatLiteral::class)
val LOG_10 =             PrometheusFunction("log10", listOf(InstantVector::class), FloatLiteral::class)
val MINUTE =             PrometheusFunction("minute", listOf(InstantVector::class), FloatLiteral::class)
val MINUTE_NOW =         PrometheusFunction("minute", listOf(), FloatLiteral::class)
val MONTH =              PrometheusFunction("month", listOf(InstantVector::class), FloatLiteral::class)
val MONTH_NOW =          PrometheusFunction("month", listOf(), FloatLiteral::class)
val PREDICT_LINEAR =     PrometheusFunction("predict_linear", listOf(RangeVector::class, FloatLiteral::class), FloatLiteral::class)
val RATE =               PrometheusFunction("rate", listOf(RangeVector::class), FloatLiteral::class)
val RESETS =             PrometheusFunction("resets", listOf(RangeVector::class), FloatLiteral::class)
val ROUND =              PrometheusFunction("round", ) // TODO
val SCALAR =             PrometheusFunction("scalar", listOf(InstantVector::class), FloatLiteral::class)
val SGN =                PrometheusFunction("sgn", listOf(InstantVector::class), InstantVector::class)
val SORT =               PrometheusFunction("sort", listOf(InstantVector::class), InstantVector::class)
val SORT_DESC =          PrometheusFunction("sort_desc", listOf(InstantVector::class), InstantVector::class)
val SQRT =               PrometheusFunction("sqrt", listOf(InstantVector::class), FloatLiteral::class)
val TIME =               PrometheusFunction("time", listOf(), FloatLiteral::class)
val TIMESTAMP =          PrometheusFunction("timestamp", listOf(InstantVector::class), FloatLiteral::class)
val VECTOR =             PrometheusFunction("vector", listOf(FloatLiteral::class), InstantVector::class)
val YEAR =               PrometheusFunction("year", listOf(InstantVector::class), FloatLiteral::class)
val YEAR_NOW =           PrometheusFunction("year", listOf(), FloatLiteral::class)

////
/// Aggregate functions, as per https://prometheus.io/docs/prometheus/2.31/querying/functions/#aggregation_over_time
////

val AVG_OVER_TIME =      PrometheusFunction("avg_over_time")
val MIN_OVER_TIME =      PrometheusFunction("min_over_time")
val MAX_OVER_TIME =      PrometheusFunction("max_over_time")
val SUM_OVER_TIME =      PrometheusFunction("sum_over_time")
val COUNT_OVER_TIME =    PrometheusFunction("count_over_time")
val QUANTILE_OVER_TIME = PrometheusFunction("quantile_over_time")
val STDDEV_OVER_TIME =   PrometheusFunction("stddev_over_time")
val STDVAR_OVER_TIME =   PrometheusFunction("stdvar_over_time")
val LAST_OVER_TIME =     PrometheusFunction("last_over_time")
val PRESENT_OVER_TIME =  PrometheusFunction("present_over_time")

////
/// Trigonometric functions, as per https://prometheus.io/docs/prometheus/2.31/querying/functions/#trigonometric-functions
////

val ACOS =  PrometheusFunction("acos")
val ACOSH = PrometheusFunction("acosh")
val ASIN =  PrometheusFunction("asin")
val ASINH = PrometheusFunction("asinh")
val ATAN =  PrometheusFunction("atan")
val ATANH = PrometheusFunction("atanh")
val COS =   PrometheusFunction("cos")
val COSH =  PrometheusFunction("cosh")
val SIN =   PrometheusFunction("sin")
val SINH =  PrometheusFunction("sinh")
val TAN =   PrometheusFunction("tan")
val TANH =  PrometheusFunction("tanh")
val DEG =   PrometheusFunction("deg")
val PI =    PrometheusFunction("pi")
val RAD =   PrometheusFunction("rad")
