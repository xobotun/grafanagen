package com.xobotun.grafanagen.model.grafana

/**
 * Describes the interval the dashboard displays. [from] and [to] are quite obvious.
 *
 * TODO: find out the acceptable format, like `now`, ISO-8601 and unix timestamps.
 *
 * Seems like from [sources](https://github.com/grafana/grafana/blob/v6.2.x/packages/grafana-ui/src/utils/datemath.ts),
 * accepted formats are ISO-8601 (`YYYY-MM-DDTHH:mm:ss.SSSZ`), and `now + optional mathematical operation`:
 *  - `-` that removes some time from `now`
 *  - `+` that adds some time to `now`
 *  - `/` that rounds `now` to some time unit, e.g. `now/d` yield the start of the current day and
 *        `now/y` yields the start of the current year
 *
 *  Time units are: `'s', 'm', 'h', 'd', 'w', 'M', 'y'`, and their spans are pretty obvious.
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
