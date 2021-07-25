package com.xobotun.grafanagen.model.grafana.panel

/**
 * An abstract widget with bare minumum of fields.
 *
 * [Source](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/dashboard/state/PanelModel.ts#L78-L110)
 *
 */
abstract class AbstractPanel(
    /** Widget's id on the dashboard? TODO */
    val id: Int,
    /** Widget's bounding box. */
    val gridPos: PanelDimensions,
    /** Widget's title. */
    val title: String,
    val alert: Any? = null,
    val scopedVars: Any? = null,
    val repeat: String? = null,
    val repeatIteration: Int? = null,
    val repeatPanelId: Int? = null,
    val repeatDirection: String? = null,
    val repeatedByRow: Boolean? = null,
    val maxPerRow: Int? = null,
    val collapsed: Boolean? = null,
    val panels: Any? = null,
    val soloMode: Boolean? = null,
    val targets: Any? = null,
    val datasource: String? = null,
    val thresholds: Any? = null,
    val pluginVersion: String? = null,
    val snapshotData: Any? = null,
    val timeFrom: Any? = null,
    val timeShift: Any? = null,
    val hideTimeOverride: Any? = null,
    val options: Any? = null,
    val maxDataPoints: Int? = null,
    val interval: String? = null,
    val description: String? = null,
    val links: List<Any>? = null,
    val transparent: Boolean = false,
) {
    /** Widget's type that determines its' fields and display mode. TODO: get a list of non-plugins types somewhere. */
    abstract val type: String
}
