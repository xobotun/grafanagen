package com.xobotun.grafanagen.model.grafana.panel

import com.xobotun.grafanagen.model.grafana.AbstractDataTarget
import com.xobotun.grafanagen.model.grafana.DashboardLink
import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget

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
    override val targets: List<AbstractDataTarget>? = listOf(GrafanaGenericDataTarget()),
    override val datasource: String? = null,
    override val thresholds: List<GraphThreshold>? = emptyList(),
    override val pluginVersion: String? = null,
    override val snapshotData: Any? = null,
    override val timeFrom: String? = null,
    override val timeShift: String? = null,
    override val hideTimeOverride: Boolean? = null,
    override val options: Any? = Object(),
    override val maxDataPoints: Int? = null,
    override val interval: String? = null,
    override val description: String? = null,
    override val links: List<DashboardLink>? = null,
    override val transparent: Boolean? = null,

    /** TODO: comments */
    val aliasColors: Any? = Unit,
    val bars: Boolean = false,
    val dashLength: Int = 10,
    val dashes: Boolean = false,
    val fill: Int = 1, // Is that a 0/1 boolean?
    val legend: GraphLegend = GraphLegend(),
    /** Precision in tooltips and legend. */
    val decimals: Int? = null,
    val lines: Boolean = true,
    val linewidth: Int = 1,
    val nullPointMode: String = "null",
    val percentage: Boolean = false,
    val pointradius: Int = 2,
    val points: Boolean = false,
    val renderer: String = "flot",
    val seriesOverrides: List<Any> = emptyList(),
    val spaceLength: Int = 10,
    val stack: Boolean = false,
    val steppedLine: Boolean = false,
    val timeRegions: List<GraphTimeRegion> = emptyList(),
    val tooltip:GraphTooltip = GraphTooltip(),
    val xaxis: GraphXAxisConfig = GraphXAxisConfig(),
    val yaxes: List<GraphYAxisConfig> = listOf(GraphYAxisConfig(), GraphYAxisConfig()),
    val yaxis: GraphYAxesPairingConfig = GraphYAxesPairingConfig(),
) : AbstractPanel {
    companion object NullPointMode {
        const val NULL = "null"
        const val CONNECTED = "connected"
        const val NULL_AS_ZERO = "null as zero"
    }
}

/**
 * Defines what additional data will be shown near each legend entry.
 * Probably works only with graph widgets.
 * TODO: comments for each fields. Though they are pretty self-explanatory, imho.
 */
data class GraphLegend(
    val avg: Boolean = false,
    val current: Boolean = false,
    val max: Boolean = false,
    val min: Boolean = false,
    val show: Boolean = true,
    val total: Boolean = false,
    val values: Boolean = false,
    val alignAsTable: Boolean? = null,
    val rightSide: Boolean? = null,
    val hideEmpty: Boolean? = null,
    val hideZero: Boolean? = null,
    val sideWidth: Int? = null // Present only when rightSide == true.
)

/**
 * Defines what info is shown on mouse over.
 */
data class GraphTooltip(
    val shared: Boolean = true,
    val sort: Int = SortOrder.NONE,
    val value_type: String = ValueType.INDIVIDUAL
) {
    class ValueType {
        companion object {
            /** Shows true values. */
            const val INDIVIDUAL = "individual"
            /** Sums up values (TODO: in order the series are displayed?). */
            const val CUMULATIVE = "cumulative"
        }
    }
    /** Affects only tooltip, not how the series are shown. */
    class SortOrder {
        companion object {
            const val NONE = 0
            const val INCREASING = 1
            const val DECREASING = 2
        }
    }
}

/**
 * Configures visual appearance of the x axis.
 */
data class GraphXAxisConfig(
    val buckets: Any? = null,
    val mode: String = "time",
    val name: Any? = null,
    val show: Boolean = true,
    val values: List<Any> = emptyList(),
)

/**
 * Configures visual appearance of the y axis.
 * There are two y axis: left and right. Though it is not clear when the right one appears.
 */
data class GraphYAxisConfig(
    val format: String = "short",
    val label: String? = null,
    val logBase: Int = 1, // 1 for linear, others are 2, 10, 32 and 1024
    val max: String? = null,
    val min: String? = null,
    val show: Boolean = true,
    /** Precision on the axis. */
    val decimals: Int? = null,
)

/**
 * TODO: have never used this piece.
 */
data class GraphYAxesPairingConfig(
    val align: Boolean = false,
    val alignLevel: Number? = null
)

/** Horizontal ruler on a graph widget */
data class GraphThreshold(
    /** Line height */
    val value: Int = 100,
    val colorMode: String = ColorMode.WARNING,
    /** `>` or `<` */
    val op: String = Operation.GT,
    /** Fill the space above/below the line */
    val fill: Boolean = true,
    /** Whether to draw the line */
    val line: Boolean = true,
    val yaxis: String = YAxis.LEFT,
    /** Used when [colorMode] == [ColorMode.CUSTOM] */
    val fillColor: String? = null,
    /** Used when [colorMode] == [ColorMode.CUSTOM] */
    val lineColor: String? = null
) {
    class ColorMode {
        companion object {
            const val OK = "ok"
            const val WARNING = "warning"
            const val CRITICAL = "critical"
            const val CUSTOM = "custom"
        }
    }
    /** Affects only tooltip, not how the series are shown. */
    class Operation {
        companion object {
            /** Warning when series greater than threshold */
            const val GT = "gt"
            /** Warning when series below the threshold */
            const val LT = "lt"
        }
    }
    /** Affects only tooltip, not how the series are shown. */
    class YAxis {
        companion object {
            /** Left y axis */
            const val LEFT = "left"
            /** Right y axis */
            const val RIGHT = "right"
        }
    }
}

/** Horizontal ruler on a graph widget */
data class GraphTimeRegion(
    /** Some magic constant */
    val op: String = "time",
    val colorMode: String = ColorMode.GRAY,
    /** Fill the space between the dates */
    val fill: Boolean = true,
    /** Whether to draw the lines around the fill */
    val line: Boolean = true,
    /** From time: hh:MM */
    val from: String? = null,
    /** To time: hh:MM */
    val to: String? = null,
    /** From time: hh:MM */
    val fromDayOfWeek: Int? = DayOfWeek.ANY,
    /** To time: hh:MM */
    val toDayOfWeek: Int? = DayOfWeek.ANY,
    /** Used when [colorMode] == [ColorMode.CUSTOM] */
    val fillColor: String? = null,
    /** Used when [colorMode] == [ColorMode.CUSTOM] */
    val lineColor: String? = null
) {
    class ColorMode {
        companion object {
            const val GRAY = "gray"
            const val RED = "red"
            const val GREEN = "green"
            const val BLUE = "blue"
            const val YELLOW = "yellow"
            const val CUSTOM = "custom"
        }
    }
    class DayOfWeek {
        companion object {
            val ANY: Int? = null
            const val MONDAY = 1
            const val TUESDAY = 2
            const val WEDNESDAY = 3 // Dude!
            const val THURSDAY = 4
            const val FRIDAY = 5
            const val SATURDAY = 6
            const val SUNDAY = 7
        }
    }
}
