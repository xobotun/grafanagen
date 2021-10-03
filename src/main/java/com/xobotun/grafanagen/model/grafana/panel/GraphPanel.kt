package com.xobotun.grafanagen.model.grafana.panel

import com.xobotun.grafanagen.model.grafana.DataTarget
import com.xobotun.grafanagen.model.grafana.PluginVersion

/**
 * A graph panel. Has default state declared [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/graph/module.ts#L32).
 */
data class GraphPanel(
    override val id: Int,
    override val gridPos: PanelDimensions,
    override val type: String = BuiltInType.GRAPH_PANEL,
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
) : AbstractPanel {

}
