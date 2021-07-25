package com.xobotun.grafanagen.model.grafana.panel

/**
 * A simple text panel.
 */
class TextPanel(
    title: String,
    gridPos: PanelDimensions,
    id: Int,
    /** Text display mode. TODO: find more modes */
    val mode: String = "markdown",
    /** Text content. TODO: probably needs support for variables and queries. */
    val content: String
) : AbstractPanel(
    title,
    gridPos,
    id
) {
    override val type = "text"
}
