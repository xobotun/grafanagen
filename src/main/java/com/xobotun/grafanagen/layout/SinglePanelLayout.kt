package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.model.grafana.panel.AbstractPanel
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.DEFAULT_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MAX_WIDTH
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_HEIGHT
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions.Companion.MIN_WIDTH

/**
 * The final layout for anything.
 * Actually configures dimensions of the panel.
 */
class SinglePanelLayout(
    parent: Layout,
    var panel: AbstractPanel,
    minWidth: Int = MIN_WIDTH,
    minHeight: Int = MIN_HEIGHT,
    desiredWidth: Int = MAX_WIDTH,
    desiredHeight: Int = DEFAULT_HEIGHT
) : AbstractLayout(parent, minWidth, minHeight, desiredWidth, desiredHeight) {
    override val children: List<Layout> get() = emptyList()

    override fun tryFitInto(widthLimit: Int, heightLimit: Int) {
        super.tryFitInto(widthLimit, heightLimit)
        panel.gridPos = panel.gridPos.copy(w = finalWidth, h = finalHeight)
    }

    override fun updatePanelCoordinates(layoutTopLeftX: Int, layoutTopLeftY: Int) {
        panel.gridPos = panel.gridPos.copy(x = layoutTopLeftX, y = layoutTopLeftY)
    }
}
