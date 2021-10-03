package com.xobotun.grafanagen.model.grafana

/**
 * A class that holds an expression that shows the data
 * along with some metadata.
 *
 * Unique to the widget and datasource.
 *
 * TODO: find this in the sources and plan a class hierarchy
 */
interface AbstractDataTarget {
    /** Name by which this series is referenced to */
    val refId: String get() = "A"
    val alias: String? get() = null
}

/**
 * Grafana built-in datatarget. Provides a widget with a generic data series.
 */
data class GrafanaGenericDataTarget(
    override val refId: String = "A",
    override val alias: String? = null,
    val scenarioId: String = "random_walk",
    val stringInput: String? = null
): AbstractDataTarget

/**
 * Prometheus-related datatarget. Should also suit VictoriaMetrics, as they seem to share the protocol.
 */
data class PrometheusDataTarget(
    override val refId: String = "A",
    override val alias: String? = null,
    /** Prometheus query. */
    val expr: String,
    val format: String = "time_series",
    val instant: Boolean? = false,
    val intervalFactor: Int = 1,
    /** Whether to display raw series name with labels or something custom */
    val legendFormat: String? = null,
): AbstractDataTarget
