package com.xobotun.grafanagen.model.grafana

/**
 * How the mouse position is handled above all the graphs on the dashboard.
 */
enum class GraphTooltip(
    /** Internal Grafana ordinal number. Just because code is prone to shuffling errors */
    val ord: Int
) {
    /** Mouse cursor shows vertical line and tooltip only for the graph it is over */
    NOTHING_SHARED(0),
    /** Mouse shows same vertical line on all the graphs */
    SHARED_CROSSHAIR(1),
    /** Mouse shows same vertical line and series tooltips on the graphs */
    SHARED_CROSSHAIR_AND_TOOLTIP(2),
}
