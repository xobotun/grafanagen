package com.xobotun.grafanagen.serde.panel

import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget
import com.xobotun.grafanagen.model.grafana.panel.GraphPanel
import com.xobotun.grafanagen.model.grafana.panel.PanelDimensions
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

private val rawGraphPanel2 = """
{
      "aliasColors": {},
      "bars": false,
      "dashLength": 10,
      "dashes": false,
      "fill": 1,
      "gridPos": {
        "h": 8,
        "w": 12,
        "x": 0,
        "y": 0
      },
      "id": 4,
      "legend": {
        "avg": false,
        "current": false,
        "max": false,
        "min": false,
        "show": true,
        "total": false,
        "values": false
      },
      "lines": true,
      "linewidth": 1,
      "nullPointMode": "null",
      "options": {},
      "percentage": false,
      "pointradius": 2,
      "points": false,
      "renderer": "flot",
      "seriesOverrides": [],
      "spaceLength": 10,
      "stack": false,
      "steppedLine": false,
      "thresholds": [],
      "timeFrom": null,
      "timeRegions": [],
      "timeShift": null,
      "title": "Panel Title",
      "tooltip": {
        "shared": true,
        "sort": 0,
        "value_type": "individual"
      },
      "type": "graph",
      "xaxis": {
        "buckets": null,
        "mode": "time",
        "name": null,
        "show": true,
        "values": []
      },
      "yaxes": [
        {
          "format": "short",
          "label": null,
          "logBase": 1,
          "max": null,
          "min": null,
          "show": true
        },
        {
          "format": "short",
          "label": null,
          "logBase": 1,
          "max": null,
          "min": null,
          "show": true
        }
      ],
      "yaxis": {
        "align": false,
        "alignLevel": null
      }
    }
""".trimIndent()

private val dtoGraphPanel1 = GraphPanel(
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 8),
    id = 2,
    targets = listOf(GrafanaGenericDataTarget()),
    title = "Panel Title",
)

private val dtoGraphPanel2 = GraphPanel(
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 8),
    id = 4,
    targets = null,
    title = "Panel Title",
)

val defaultGraphPanel1 = SerdeExample.of("Default graph panel 1", dtoGraphPanel1, rawGraphPanel1)
val defaultGraphPanel2 = SerdeExample.of("Default graph panel 2", dtoGraphPanel2, rawGraphPanel2)
