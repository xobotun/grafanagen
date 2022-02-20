package com.xobotun.grafanagen.layout

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

/**
 * Tests both [SinglePanelLayout] and [AbstractLayout].
 */
internal class SinglePanelLayoutTest {

    @Test
    fun `AbstractPanel does not grow if plenty of space`() {
        val layout = singlePanelSupplier()
        layout.tryFitInto(9, 10)
        assertEquals(4, layout.panel.gridPos.w, "AbstractPanel should not grow from desired 4 width to allowed 9")
        assertEquals(5, layout.panel.gridPos.h, "AbstractPanel should not grow from desired 5 height to allowed 10")
    }

    @Test
    fun `AbstractPanel shrinks if between minimal and desired`() {
        val layout = singlePanelSupplier()
        layout.tryFitInto(3, 4)
        assertEquals(3, layout.panel.gridPos.w, "AbstractPanel should shrink to provided 3 width when between 2 and 4")
        assertEquals(4, layout.panel.gridPos.h, "AbstractPanel should shrink to provided 4 height when between 3 and 5")
    }

    @Test
    fun `AbstractPanel delegates to GlobalConfig when allotted space is below minimal`() {
        val layout = singlePanelSupplier()
        assertThrows<ConfigException>("AbstractPanel should behave as specified in GlobalConfig when shrinks below minimal size") {
            layout.tryFitInto(1, 1)
        }
    }

    @Test
    fun `SinglePanelLayout sets gridPos-w and gridPos-h to finalWidth and finalHeight`() {
        val layout = singlePanelSupplier()
        layout.tryFitInto(3, 4)
        assertEquals(layout.finalWidth, layout.panel.gridPos.w, "SinglePanelLayout should set panel's width to layouts final width")
        assertEquals(layout.finalHeight, layout.panel.gridPos.h, "SinglePanelLayout should set panel's height to layouts final height")

        val defaultXY = singlePanelSupplier().panel.gridPos
        assertEquals(defaultXY.x, layout.panel.gridPos.x, "SinglePanelLayout should not touch panel's x coordinate")
        assertEquals(defaultXY.y, layout.panel.gridPos.y, "SinglePanelLayout should not touch panel's y coordinate")
    }

    @Test
    fun `SinglePanelLayout sets gridPos-x and gridPos-y to what is asked`() {
        val layout = singlePanelSupplier()
        layout.updatePanelCoordinates(1, 2)
        assertEquals(1, layout.panel.gridPos.x, "SinglePanelLayout should set panel's x coordinate to whatever provided")
        assertEquals(2, layout.panel.gridPos.y, "SinglePanelLayout should set panel's y coordinate to whatever provided")

        val defaultWH = singlePanelSupplier().panel.gridPos
        assertEquals(defaultWH.w, layout.panel.gridPos.w, "SinglePanelLayout should not touch panel's width")
        assertEquals(defaultWH.h, layout.panel.gridPos.h, "SinglePanelLayout should not touch panel's height")
    }

    companion object {
        val singlePanelSupplier = {
            SinglePanelLayout(
                parent = null, panel = dummyTextPanel(),
                minWidth = 2, minHeight = 3, desiredWidth = 4, desiredHeight = 5
            )
        }

        class ConfigException : Exception()
    }
}
