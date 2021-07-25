package com.xobotun.grafanagen.model.grafana.panel

/**
 * A simple text panel. Comes from [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/text/module.ts#L24-L25).
 * Or [here](https://github.com/grafana/grafana/blob/v6.2.x/public/app/plugins/panel/text2/types.ts). Why are there two copies anyway?
 */
class TextPanel(
    id: Int,
    gridPos: PanelDimensions,
    title: String,
    /** Text display mode. */
    val mode: Mode = Mode.MARKDOWN,
    /** Text content. TODO: probably needs support for variables and queries. */
    val content: String
) : AbstractPanel(
    id,
    gridPos,
    title
) {
    override val type = "text"

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
