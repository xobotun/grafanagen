package com.xobotun.grafanagen.model.grafana.panel

/**
 * A rectangle in a specific coordinate system that describes widget's position on the dashboard.
 * TODO: new data types, maybe?
 */
data class PanelDimensions(
    /** x-coordinate position of the widget on the dashboard. One unit is 1 column out of 24. */
    val x: Int,
    /** y-coordinate position of the widget. One unit is 30 px. */
    val y: Int,
    /** Width of the widget. One unit is 1 column out of 24.*/
    val w: Int,
    /** Height of the widget. One unit is 30 px. */
    val h: Int,
)

