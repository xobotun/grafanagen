package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.DEFAULT_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MAX_WIDTH
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_WIDTH
import kotlin.math.roundToInt

/**
 * Vertical layout that attempts to preserve proportions of the children layouts.
 */
class VerticalLayout(
    parent: Layout?,
    override val children: List<Layout>,
    minWidth: Int = MIN_WIDTH,
    minHeight: Int = MIN_HEIGHT,
    desiredWidth: Int = MAX_WIDTH,
    desiredHeight: Int = DEFAULT_HEIGHT
) : AbstractLayout(parent, minWidth, minHeight, desiredWidth, desiredHeight) {

    override fun tryFitInto(widthLimit: Int, heightLimit: Int) {
        super.tryFitInto(widthLimit, heightLimit)

        val weights = children.map { it.desiredHeight }
        val totalWeight = weights.sum()
        val ratios = weights.map { it * 1.0 / totalWeight }
        val layoutToRatios = children.zip(ratios)

        var remainingHeight = finalHeight
        layoutToRatios.forEach {
            val heightForThis = (finalHeight * it.second).toInt()
            remainingHeight -= heightForThis
            it.first.tryFitInto(finalWidth, heightForThis)
        }

        // If something remains, add it to the last one.
        children.last().finalHeight += remainingHeight
    }

    override fun updatePanelCoordinates(layoutTopLeftX: Int, layoutTopLeftY: Int) {
        var currentY = layoutTopLeftY
        children.forEach {
            it.updatePanelCoordinates(layoutTopLeftX, currentY)
            currentY += it.finalHeight
        }
    }
}
