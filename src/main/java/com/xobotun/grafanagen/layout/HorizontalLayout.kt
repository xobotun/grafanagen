package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.DEFAULT_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MAX_WIDTH
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_WIDTH
import kotlin.math.roundToInt

/**
 * Horizontal layout that attempts to preserve proportions of the children layouts.
 */
class HorizontalLayout(
    parent: Layout?,
    override val children: List<Layout>,
    minWidth: Int = MIN_WIDTH,
    minHeight: Int = MIN_HEIGHT,
    desiredWidth: Int = MAX_WIDTH,
    desiredHeight: Int = DEFAULT_HEIGHT
) : AbstractLayout(parent, minWidth, minHeight, desiredWidth, desiredHeight) {

    override fun tryFitInto(widthLimit: Int, heightLimit: Int) {
        super.tryFitInto(widthLimit, heightLimit)

        val weights = children.map { it.desiredWidth }
        val totalWeight = weights.sum()
        val ratios = weights.map { it * 1.0 / totalWeight }
        val layoutToRatios = children.zip(ratios)

        var remainingWidth = finalWidth
        layoutToRatios.forEach {
            val widthForThis = (finalWidth * it.second).toInt()
            remainingWidth -= widthForThis
            it.first.tryFitInto(widthForThis, finalHeight)
        }

        // If something remains, add it to the last one.
        children.last().finalWidth += remainingWidth
    }

    override fun updatePanelCoordinates(layoutTopLeftX: Int, layoutTopLeftY: Int) {
        var currentX = layoutTopLeftX
        children.forEach {
            it.updatePanelCoordinates(currentX, layoutTopLeftY)
            currentX += it.finalWidth
        }
    }
}
