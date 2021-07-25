package com.xobotun.grafanagen.model.grafana

import java.math.BigInteger

/**
 * An annotation. They seem broken on our installation so I've never got to use them.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/annotations/editor_ctrl.ts#L17-L22).
 */
data class Annotation(
    /** TODO: Figure out. A shown name, obviously/ */
    val name: String,
    /** TODO: Figure out. Where it is stored in, maybe? */
    val datasource: String,
    /** TODO: Figure out. It has icons, it seems. Or is it the color of the region? Anyway, TODO: introduce RGBA color class. */
    val iconColor: String,
    /** TODO: Figure out. Whether it is shown? */
    val enable: BigInteger,
    /** TODO: Figure out. Looks like a panel id or enum. */
    val showIn: Int,
    /** TODO: Figure out. An ability to hide annotations? */
    val hide: Boolean,
)
