package com.xobotun.grafanagen.model.grafana.templating

/**
 * Whether to hide the variable or not.
 */
enum class HideVariable(
    /** Internal Grafana ordinal number. Just because code is prone to shuffling errors */
    val ord: Int
) {
    /** The variable is shown at the top of the dashboard. */
    SHOW(0),
    /** TODO: find the behaviour */
    UNKNOWN(1),
    /** The variable is hidden. */
    HIDDEN(2),
}
