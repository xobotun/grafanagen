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
}

/**
 * Grafana built-in datatarget. Provides a graph widget with a generic data series.
 */
data class GrafanaGraphGenericDataTarget(
    override val refId: String = "A",
    val scenarioId: String = "random_walk",
    val stringInput: String? = null
): AbstractDataTarget

/**
 * Prometheus-related datatarget for graphs. Should also suit VictoriaMetrics, as they seem to share the protocol.
 */
data class PrometheusGraphDataTarget(
    override val refId: String = "A",
    /** Prometheus query. */
    val expr: String,
    val format: String = "time_series",
    val instant: Boolean? = false,
    val intervalFactor: Int = 1,
    /** Whether to display raw series name with labels or something custom */
    val legendFormat: String? = null,
): AbstractDataTarget
