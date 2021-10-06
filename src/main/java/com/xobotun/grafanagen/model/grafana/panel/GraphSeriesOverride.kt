package com.xobotun.grafanagen.model.grafana.panel

/**
 * Customizes how the series are drawn.
 */
data class GraphSeriesOverride(
    /** Series name or regex */
    val alias: String,
    /** Draw vetical bars */
    val bars: Boolean? = null,
    /** Draw lines */
    val lines: Boolean? = null,
    /** Alpha channel of the fill. 0-10 */
    val fill: Int? = null,
    /** Line width. 0-10 */
    val linewidth: Int? = null,
    /** How areas with no data are treated. [GraphPanel.NullPointMode] */
    val nullPointMode: String? = null,
    /** Fills the region between this ano another series [alias], not between this series and zero */
    val fillBelowTo: String? = null,
    /** Draw rectangular lines instead of straight lines */
    val steppedLine: Boolean? = null,
    /** Draw dashes */
    val dashes: Boolean? = null,
    /** Dash length. 1-20 */
    val dashLength: Int? = null,
    /** Anti-dash length. 1-20 */
    val spaceLength: Int? = null,
    /** Draw points */
    val points: Boolean? = null,
    /** How large the points are. 1-5 */
    val pointradius: Int? = null,
    /** Specifies how will the stacking works. Booleans: true, false, Strings: "A", "B", "C" or "D" */
    val stack: Any? = null,
    /** Which y-axis is used. 1 == left, 2 == right */
    val yaxis: Int? = null,
    /** What virtual plane to draw on. -3 to 3 */
    val zindex: Int? = null,
    /** Mirrors the series across zero on the y axis. Takes only "negative-Y" */
    val transform: String? = null,
    /** Whether to show this series in the legend */
    val legend: Boolean? = null,
    /** Whether to hide this series in th the tooltip */
    val hideTooltip: Boolean? = null,
    /** Series color */
    val color: String? = null,
)
