package com.xobotun.grafanagen.serde.panel

import com.xobotun.grafanagen.model.grafana.DataTarget
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions
import com.xobotun.grafanagen.model.grafana.panel.TextPanel
import com.xobotun.grafanagen.serde.SerdeExample

private val rawGraphPanel1 = """
    {
      "type": "graph",
      "title": "Panel Title",
      "gridPos": {
        "x": 0,
        "y": 0,
        "w": 12,
        "h": 8
      },
      "id": 2,
      "targets": [
        {
          "refId": "A",
          "scenarioId": "random_walk"
        }
      ],
      "options": {},
      "renderer": "flot",
      "yaxes": [
        {
          "label": null,
          "show": true,
          "logBase": 1,
          "min": null,
          "max": null,
          "format": "short"
        },
        {
          "label": null,
          "show": true,
          "logBase": 1,
          "min": null,
          "max": null,
          "format": "short"
        }
      ],
      "xaxis": {
        "show": true,
        "mode": "time",
        "name": null,
        "values": [],
        "buckets": null
      },
      "yaxis": {
        "align": false,
        "alignLevel": null
      },
      "lines": true,
      "fill": 1,
      "linewidth": 1,
      "dashes": false,
      "dashLength": 10,
      "spaceLength": 10,
      "points": false,
      "pointradius": 2,
      "bars": false,
      "stack": false,
      "percentage": false,
      "legend": {
        "show": true,
        "values": false,
        "min": false,
        "max": false,
        "current": false,
        "total": false,
        "avg": false
      },
      "nullPointMode": "null",
      "steppedLine": false,
      "tooltip": {
        "value_type": "individual",
        "shared": true,
        "sort": 0
      },
      "timeFrom": null,
      "timeShift": null,
      "aliasColors": {},
      "seriesOverrides": [],
      "thresholds": [],
      "timeRegions": []
    }
""".trimIndent()

private val dtoGraphPanel1 = TextPanel(
    content = "Some content",
    datasource = "TestData DB",
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 9),
    id = 2,
    mode = TextPanel.Mode.HTML.shownName,
    targets = listOf(DataTarget()),
    title = "Panel Title",
)

val graphPanel1 = SerdeExample.of("Graph Panel 1", dtoGraphPanel1, rawGraphPanel1)
