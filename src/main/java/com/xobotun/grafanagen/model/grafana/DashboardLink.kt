package com.xobotun.grafanagen.model.grafana

/**
 * A link to other dashboard or external site.
 * That thing in the top-left corner of a widget.
 */
data class DashboardLink(
    val type: String = DASHBOARD,
    val keepTime: Boolean = false,
    val includeVars: Boolean = false,
    /** Opens link in a new tab */
    val targetBlank: Boolean = false,
    val title: String? = null,
    val params: String? = null,
    // Depends on type, but both can be present
    val dashboard: String? = null,
    val url: String? = null,
) {
    companion object DashbloarLinkType {
        const val DASHBOARD = "dashboard"
        const val ABSOLUTE = "absolute"
    }
}
