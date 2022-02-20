package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.DEFAULT_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MAX_WIDTH
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_WIDTH

/**
 * Just an abstract class to define defaults, constraints and a constructor.
 */
abstract class AbstractLayout(
    final override val parent: Layout?,
    final override val minWidth: Int = MIN_WIDTH,
    final override val minHeight: Int = MIN_HEIGHT,
    final override val desiredWidth: Int = MAX_WIDTH,
    final override val desiredHeight: Int = DEFAULT_HEIGHT,
) : Layout {
    override var finalWidth: Int = desiredWidth
    override var finalHeight: Int = desiredHeight

    override fun tryFitInto(widthLimit: Int, heightLimit: Int) {
        adjustIfNeeded(minWidth, desiredWidth, widthLimit, { finalWidth = widthLimit })
        adjustIfNeeded(minHeight, desiredHeight, heightLimit, { finalHeight = heightLimit })
    }
}
