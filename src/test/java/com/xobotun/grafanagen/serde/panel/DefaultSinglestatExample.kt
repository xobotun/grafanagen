package com.xobotun.grafanagen.serde.panel

import com.xobotun.grafanagen.model.grafana.DashboardLink
import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget
import com.xobotun.grafanagen.model.grafana.panel.*
import com.xobotun.grafanagen.serde.SerdeExample


private val rawSinglestatPanel1 = """
{
  "type": "singlestat",
  "title": "Panel Title",
  "gridPos": {
    "x": 0,
    "y": 0,
    "w": 12,
    "h": 8
  },
  "id": 8,
  "targets": [
    {
      "refId": "A",
      "scenarioId": "random_walk"
    }
  ],
  "timeFrom": null,
  "timeShift": null,
  "options": {},
  "links": [],
  "maxDataPoints": 100,
  "interval": null,
  "cacheTimeout": null,
  "format": "none",
  "prefix": "",
  "postfix": "",
  "nullText": null,
  "valueMaps": [
    {
      "value": "null",
      "op": "=",
      "text": "N/A"
    }
  ],
  "mappingTypes": [
    {
      "name": "value to text",
      "value": 1
    },
    {
      "name": "range to text",
      "value": 2
    }
  ],
  "rangeMaps": [
    {
      "from": "null",
      "to": "null",
      "text": "N/A"
    }
  ],
  "mappingType": 1,
  "nullPointMode": "connected",
  "valueName": "avg",
  "prefixFontSize": "50%",
  "valueFontSize": "80%",
  "postfixFontSize": "50%",
  "thresholds": "",
  "colorBackground": false,
  "colorValue": false,
  "colors": [
    "#299c46",
    "rgba(237, 129, 40, 0.89)",
    "#d44a3a"
  ],
  "sparkline": {
    "show": false,
    "full": false,
    "lineColor": "rgb(31, 120, 193)",
    "fillColor": "rgba(31, 118, 189, 0.18)"
  },
  "gauge": {
    "show": false,
    "minValue": 0,
    "maxValue": 100,
    "thresholdMarkers": true,
    "thresholdLabels": false
  },
  "tableColumn": ""
}
""".trimIndent()

private val dtoSinglestatPanel1 = SinglestatPanel(
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 8),
    id = 8,
    title = "Panel Title",
)

val defaultSinglestatPanel1 = SerdeExample.of("Complex singlestat panel 1", dtoSinglestatPanel1, rawSinglestatPanel1)
