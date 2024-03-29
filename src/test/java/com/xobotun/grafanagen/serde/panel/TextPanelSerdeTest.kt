package com.xobotun.grafanagen.serde.panel

import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions
import com.xobotun.grafanagen.model.grafana.panel.TextPanel
import com.xobotun.grafanagen.serde.SerdeExample

private val rawTextPanel1 = """
    {
      "cacheTimeout": null,
      "content": "Some content",
      "datasource": "TestData DB",
      "gridPos": {
        "h": 9,
        "w": 12,
        "x": 0,
        "y": 0
      },
      "id": 2,
      "links": [],
      "mode": "html",
      "options": {},
      "pluginVersion": "6.2.5",
      "targets": [
        {
          "refId": "A",
          "scenarioId": "random_walk",
          "stringInput": ""
        }
      ],
      "timeFrom": null,
      "timeShift": null,
      "title": "Panel Title",
      "type": "text"
    }
""".trimIndent()

private val dtoTextPanel1 = TextPanel(
    content = "Some content",
    datasource = "TestData DB",
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 9),
    id = 2,
    mode = TextPanel.Mode.HTML.shownName,
    targets = listOf(GrafanaGenericDataTarget(stringInput = "")),
    title = "Panel Title",
    transparent = null
)

val textPanel1 = SerdeExample.of("Text Panel 1", dtoTextPanel1, rawTextPanel1)
