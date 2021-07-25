package com.xobotun.grafanagen.model.grafana

/**
 * A drop-down menu in the top-right corner that allows to choose
 * refresh interval and start-end dates of the dashboard.
 *
 * Documentation example:
 * ```
 * "timepicker": {
 *   "collapse": false,
 *   "enable": true,
 *   "notice": false,
 *   "now": true,
 *   "refresh_intervals": [
 *     "5s",
 *     "10s",
 *     "30s",
 *     "1m",
 *     "5m",
 *     "15m",
 *     "30m",
 *     "1h",
 *     "2h",
 *     "1d"
 *   ],
 *   "status": "Stable",
 *   "time_options": [
 *     "5m",
 *     "15m",
 *     "1h",
 *     "3h",
 *     "6h",
 *     "12h",
 *     "24h",
 *     "2d",
 *     "3d",
 *     "4d",
 *     "7d",
 *     "30d"
 *   ],
 *   "type": "timepicker"
 * }
 * ```
 */
data class TimePicker(
    /** TODO: Figure out if that ever important. */
    val collapse: Boolean,
    /** TODO: Figure out if that ever important. */
    val enable: Boolean,
    /** TODO: Figure out what this does. */
    val notice: Boolean,
    /** TODO: Figure out what this does. */
    val now: Boolean,
    /** A set of intervals the webpage makes request to the backend. TODO: same datatype as in [time_options] and [Dashboard.refresh] */
    val refresh_intervals: List<String>,
    /** TODO: Figure out what this does. */
    val status: String,
    /** A set of zoom options on the timeline. */
    val time_options: List<String>,
    /** TODO: Figure out what this does. Probably a marker field. */
    val type: String = "timepicker",
)
