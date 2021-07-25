package com.xobotun.grafanagen.model.grafana.panel

/**
 * An abstract widget with bare minumum of fields.
 * TODO: find out what is the bare minimum of fields. Official documentation is surprisingly unhelpful.
 */
abstract class AbstractPanel(
    /** Widget's title. */
    val title: String,
    /** Widget's bounding box. */
    val gridPos: PanelDimensions,
    /** Widget's id on the dashboard? TODO */
    val id: Int
) {
    /** Widget's type that determines its' fields and display mode. TODO: get a list of non-plugins types somewhere. */
    abstract val type: String
}
