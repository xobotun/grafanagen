package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.model.grafana.panel.AbstractPanel
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions
import com.xobotun.grafanagen.model.grafana.panel.TextPanel

/**
 * Since we are instered only in [AbstractPanel.gridPos] changes in the layout tests,
 * here's a completely function panel of an irrelevenat type and irrelevant contents.
 */
fun dummyTextPanel() = TextPanel(id = 1, gridPos = PanelDimensions(0, 0, 0, 0), content = "Dummy panel", title = "Dummy panel")
