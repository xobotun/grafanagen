package com.xobotun.grafanagen.serde.panel

import com.xobotun.grafanagen.model.grafana.GrafanaGenericDataTarget
import com.xobotun.grafanagen.model.grafana.panel.*
import com.xobotun.grafanagen.model.grafana.panel.GraphPanel.NullPointMode
import com.xobotun.grafanagen.serde.SerdeExample
import com.xobotun.grafanagen.util.eq

private val rawGraphPanel1 = """
{
  "aliasColors": {},
  "bars": true,
  "dashLength": 10,
  "dashes": false,
  "decimals": 2,
  "description": "Desc",
  "fill": 6,
  "gridPos": {
    "h": 8,
    "w": 12,
    "x": 0,
    "y": 0
  },
  "id": 4,
  "legend": {
    "alignAsTable": true,
    "avg": true,
    "current": true,
    "hideEmpty": true,
    "hideZero": true,
    "max": true,
    "min": true,
    "rightSide": true,
    "show": true,
    "sideWidth": 500,
    "total": true,
    "values": true
  },
  "lines": true,
  "linewidth": 4,
  "links": [
    {
      "type": "dashboard",
      "keepTime": true,
      "includeVars": true,
      "targetBlank": true,
      "dashboard": "test",
      "title": "title",
      "params": "testparam"
    }
  ],
  "nullPointMode": "connected",
  "options": {},
  "percentage": true,
  "pointradius": 7,
  "points": true,
  "renderer": "flot",
  "repeat": null,
  "seriesOverrides": [
    {
      "alias": "A-series",
      "legend": true,
      "linewidth": 0,
      "stack": "B"
    }
  ],
  "spaceLength": 10,
  "stack": true,
  "steppedLine": true,
  "targets": [
    {
      "refId": "A",
      "scenarioId": "random_walk",
      "alias": "alias-1"
    },
    {
      "refId": "B",
      "scenarioId": "random_walk",
      "alias": ""
    }
  ],
  "thresholds": [
    {
      "value": 100,
      "colorMode": "warning",
      "op": "gt",
      "fill": true,
      "line": true,
      "yaxis": "left"
    }
  ],
  "timeFrom": "15m",
  "timeRegions": [
    {
      "colorMode": "custom",
      "fill": true,
      "fillColor": "#FF9830",
      "from": "12:00",
      "fromDayOfWeek": 4,
      "line": true,
      "lineColor": "rgba(199, 132, 124, 0.6)",
      "op": "time",
      "to": "13:00",
      "toDayOfWeek": 5
    }
  ],
  "timeShift": "1m",
  "title": "Panel Title",
  "tooltip": {
    "shared": true,
    "sort": 1,
    "value_type": "cumulative"
  },
  "transparent": true,
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
      "decimals": 1,
      "format": "short",
      "label": "",
      "logBase": 1,
      "max": "300",
      "min": "0",
      "show": true
    },
    {
      "decimals": 0,
      "format": "flops",
      "label": "",
      "logBase": 10,
      "max": null,
      "min": null,
      "show": true
    }
  ],
  "yaxis": {
    "align": true,
    "alignLevel": 1
  },
  "interval": "25s",
  "hideTimeOverride": false
}
""".trimIndent()

private val dtoGraphPanel1 = GraphPanel(
    gridPos = PanelDimensions(x = 0, y = 0, w = 12, h = 8),
    id = 4,
    targets = listOf(
        GrafanaGenericDataTarget(alias = "alias-1"),
        GrafanaGenericDataTarget(refId = "B", alias = "")
    ),
    title = "Panel Title",
    description = "Desc",
    transparent = true,
    fill = 6,
    legend = GraphLegend(true, true, true, true, true, true, true, true, true, true, true, 500),
    decimals = 2,
    linewidth = 4,
    nullPointMode = NullPointMode.CONNECTED,
    percentage = true,
    pointradius = 7,
    stack = true,
    steppedLine = true,
    points = true,
    bars = true,
    tooltip = GraphTooltip(sort = GraphTooltip.SortOrder.INCREASING, value_type = GraphTooltip.ValueType.CUMULATIVE),
    yaxis = GraphYAxesPairingConfig(align = true, alignLevel = 1),
    yaxes = listOf(
        GraphYAxisConfig(label = "", show = true, logBase = 1, min = "0", max = "300", format = "short", decimals = 1),
        GraphYAxisConfig(label = "", show = true, logBase = 10, format = "flops", decimals = 0),
    ),
    timeFrom = "15m",
    timeShift = "1m",
    interval = "25s",
    hideTimeOverride = false,
)

val complexGraphPanel1 = SerdeExample.of("Complex graph panel 1", dtoGraphPanel1, rawGraphPanel1)
