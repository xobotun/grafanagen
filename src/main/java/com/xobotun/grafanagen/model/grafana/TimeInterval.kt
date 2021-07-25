package com.xobotun.grafanagen.model.grafana

/**
 * Describes the interval the dashboard displays. [from] and [to] are quite obvious.
 * TODO: find out the acceptable format, like `now`, ISO-8601 and unix timestamps.
 *
 * Documentation example:
 * ```
 * "time": {
 *   "from": "now-6h",
 *   "to": "now"
 * }
 * ```
 */
data class TimeInterval(
    val from: String,
    val to: String,
)
