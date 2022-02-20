package com.xobotun.grafanagen.model.grafana.panel

import com.xobotun.grafanagen.model.grafana.AbstractDataTarget
import com.xobotun.grafanagen.model.grafana.DashboardLink
import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget
import com.xobotun.grafanagen.model.grafana.NullPointMode
import com.xobotun.grafanagen.model.grafana.panel.SinglestatPanel.MappingType.Companion.RANGE_TO_TEXT
import com.xobotun.grafanagen.model.grafana.panel.SinglestatPanel.MappingType.Companion.VALUE_TO_TEXT

/**
 * A singlestat panel. Has default state declared [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/singlestat/module.ts#L43).
 */
data class SinglestatPanel(
    override val id: Int,
    override var gridPos: PanelDimensions,
    override val type: String = BuiltInType.SINGLESTAY_PANEL,
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
    override val targets: List<AbstractDataTarget>? = listOf(GrafanaGenericDataTarget()),
    override val datasource: String? = null,
    override val thresholds: String = "",
    override val pluginVersion: String? = null,
    override val snapshotData: Any? = null,
    override val timeFrom: String? = null,
    override val timeShift: String? = null,
    override val hideTimeOverride: Boolean? = null,
    override val options: Any? = Object(),
    override val maxDataPoints: Int? = 100,
    override val interval: String? = null,
    override val description: String? = null,
    override val links: List<DashboardLink>? = listOf(),
    override val transparent: Boolean? = null,

    val format: String = "none",
    val prefix: String = "",
    val postfix: String = "",
    val colorPrefix: Boolean? = null,
    val colorPostfix: Boolean? = null,
    val decimals: Int? = null,
    val nullText: String? = null,
    val valueMaps: List<SinglestatValueMap> = listOf(SinglestatValueMap()),
    val mappingTypes: List<MappingType> = listOf(MappingType("value to text", VALUE_TO_TEXT), MappingType("range to text", RANGE_TO_TEXT)),
    val rangeMaps: List<SinglestatRangeMap> = listOf(SinglestatRangeMap()),
    val mappingType: Int = VALUE_TO_TEXT,
    val nullPointMode: String = NullPointMode.CONNECTED,
    val valueName: String? = SinglestatMode.AVG,
    val prefixFontSize: String = "50%",
    val valueFontSize: String = "80%",
    val postfixFontSize: String = "50%",
    val colorBackground: Boolean = false,
    val colorValue: Boolean = false,
    val colors: List<String> = listOf("#299c46", "rgba(237, 129, 40, 0.89)", "#d44a3a"),
    val sparkline: SinglestatSparkline = SinglestatSparkline(),
    val gauge: SinglestatGauge = SinglestatGauge(),
    val tableColumn: String = "",
) : AbstractPanel {
    class SinglestatMode {
        companion object {
            /** Min */
            const val MIN = "min"
            /** Max */
            const val MAX = "max"
            /** Average */
            const val AVG = "avg"
            /** Current */
            const val CURRENT = "current"
            /** Total */
            const val TOTAL = "total"
            /** Name */
            const val NAME = "name"
            /** First */
            const val FIRST = "first"
            /** Delta */
            const val DELTA = "delta"
            /** Difference */
            const val DIFF = "diff"
            /** Range */
            const val RANGE = "range"
            /** Time of last point */
            const val LAST_TIME = "last_time"
        }
    }

    data class MappingType(
        val name: String,
        val value: Int,
    ) {
        companion object {
            const val VALUE_TO_TEXT = 1
            const val RANGE_TO_TEXT = 2
        }
    }
}

/** Maps one value to another */
data class SinglestatValueMap(
    /** What to map */
    val value: String = "null",
    /** Magic constant */
    val op: String = "=",
    /** To what to map */
    val text: String = "N/A",
)

/** Maps range to text */
data class SinglestatRangeMap(
    val from: String = "null",
    val to: String = "null",
    val text: String = "N/A",
)

/** Graph on the background */
data class SinglestatSparkline(
    val show: Boolean = false,
    val full: Boolean = false,
    val lineColor: String = "rgb(31, 120, 193)",
    val fillColor: String = "rgba(31, 118, 189, 0.18)"
)

/** Gauge on the foreground */
data class SinglestatGauge(
    val show: Boolean = false,
    val minValue: Int = 0,
    val maxValue: Int = 100,
    val thresholdMarkers: Boolean = true,
    val thresholdLabels: Boolean = false,
)
