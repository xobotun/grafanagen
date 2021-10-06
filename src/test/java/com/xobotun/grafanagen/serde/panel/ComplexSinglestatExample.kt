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
  "id": 6,
  "datasource": "-- Grafana --",
  "timeFrom": null,
  "timeShift": null,
  "options": {},
  "links": [],
  "maxDataPoints": 100,
  "interval": null,
  "cacheTimeout": null,
  "format": "areaF2",
  "prefix": "pre",
  "postfix": "post",
  "nullText": null,
  "valueMaps": [
    {
      "value": "null",
      "op": "=",
      "text": "N/A"
    },
    {
      "value": "2",
      "op": "=",
      "text": "3"
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
      "from": "1",
      "to": "2",
      "text": "N/A"
    }
  ],
  "mappingType": 1,
  "nullPointMode": "connected",
  "valueName": "avg",
  "prefixFontSize": "50%",
  "valueFontSize": "80%",
  "postfixFontSize": "50%",
  "thresholds": "50,80,90",
  "colorBackground": true,
  "colorValue": true,
  "colors": [
    "#299c46",
    "rgba(237, 129, 40, 0.89)",
    "#d44a3a",
    "#ffffff"
  ],
  "sparkline": {
    "show": true,
    "full": true,
    "lineColor": "rgb(31, 120, 193)",
    "fillColor": "rgba(31, 118, 189, 0.18)"
  },
  "gauge": {
    "show": true,
    "minValue": 0,
    "maxValue": 100,
    "thresholdMarkers": true,
    "thresholdLabels": true
  },
  "tableColumn": "",
  "colorPrefix": true,
  "colorPostfix": true,
  "decimals": 1,
  "repeat": "test",
  "repeatDirection": "h",
  "maxPerRow": 3
}
""".trimIndent()

private val dtoSinglestatPanel1 = SinglestatPanel(
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 8),
    id = 6,
    title = "Panel Title",
    targets = null,
    datasource = "-- Grafana --",
    links = emptyList(),
    maxDataPoints = 100,
    colorPrefix = true,
    colorPostfix = true,
    decimals = 1,
    repeat = "test",
    repeatDirection = "h",
    maxPerRow = 3,
    thresholds = "50,80,90",
    format = "areaF2", // TODO: all types
    prefix = "pre",
    postfix = "post",
    colorBackground = true,
    colorValue = true,
    colors = listOf("#299c46", "rgba(237, 129, 40, 0.89)", "#d44a3a", "#ffffff"),
    valueMaps = listOf(SinglestatValueMap(), SinglestatValueMap(value = "2", text = "3")),
    rangeMaps = listOf(SinglestatRangeMap(from = "1", to = "2")),
    sparkline = SinglestatSparkline(show = true, full = true, lineColor = "rgb(31, 120, 193)", fillColor = "rgba(31, 118, 189, 0.18)"),
    gauge = SinglestatGauge(show = true, thresholdLabels = true)
)

val complexSinglestatPanel1 = SerdeExample.of("Complex singlestat panel 1", dtoSinglestatPanel1, rawSinglestatPanel1)
