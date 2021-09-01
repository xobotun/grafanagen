package com.xobotun.grafanagen.model.grafana.panel

import com.xobotun.grafanagen.model.grafana.DataTarget
import com.xobotun.grafanagen.model.grafana.PluginVersion

/**
 * A simple text panel. Comes from [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/text/module.ts#L24-L25).
 * Or [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/text2/types.ts). Why are there two copies anyway?
 */
data class TextPanel(
    override val id: Int,
    override val gridPos: PanelDimensions,
    override val type: String = BuiltInType.TEXT_PANEL,
    override val title: String,
    override val alert: Any? = null,
    override val scopedVars: Any? = null,
    override val repeat: String? = null,
    override val repeatIteration: Int? = null,
    override val repeatPanelId: Int? = null,
    override val repeatDirection: String? = null,
    override val repeatedByRow: Boolean? = null,
    override val maxPerRow: Int? = null,
    override val collapsed: Boolean? = null,
    override val panels: Any? = null,
    override val soloMode: Boolean? = null,
    override val targets: List<DataTarget>? = null,
    override val datasource: String? = null,
    override val thresholds: Any? = null,
    override val pluginVersion: String? = PluginVersion.GRAFANA,
    override val snapshotData: Any? = null,
    override val timeFrom: Any? = null,
    override val timeShift: Any? = null,
    override val hideTimeOverride: Any? = null,
    override val options: Any? = Object(),
    override val maxDataPoints: Int? = null,
    override val interval: String? = null,
    override val description: String? = null,
    override val links: List<Any>? = emptyList(),
    override val transparent: Boolean = false,

    /** Text display mode. */
    val mode: String = Mode.MARKDOWN.shownName,
    /** Text content. TODO: probably needs support for variables and queries. */
    val content: String
) : AbstractPanel {

    // TODO: make it not an enum, but some kind of an extensible collection in the builder. TODO: I've been thinking of some StringProvider interface for a long time.
    /** Internal enum for [TextPanel]. Dictates the way content is shown. Pretty obvious. */
    enum class Mode(
        /** Just for the sake of same style across the project. */
        val shownName: String
    ) {
        HTML("html"),
        MARKDOWN("markdown"),
        TEXT("text"),
    }
}
