package com.xobotun.grafanagen.model.grafana.panel

import com.xobotun.grafanagen.model.grafana.DataTarget
import com.xobotun.grafanagen.model.grafana.PluginVersion

/**
 * An abstract widget with bare minumum of fields.
 *
 * [Source](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/dashboard/state/PanelModel.ts#L78-L110)
 *
 */
interface AbstractPanel {
    /** Widget's id on the dashboard? TODO */
    val id: Int
    /** Widget's bounding box. */
    val gridPos: PanelDimensions
    /** Widget's type that determines its' fields and display mode. TODO: get a list of non-plugins types somewhere. */
    val type: String
    /** Widget's title. */
    val title: String

    val alert: Any?                get() = null
    val scopedVars: Any?           get() = null
    val repeat: String?            get() = null
    val repeatIteration: Int?      get() = null
    val repeatPanelId: Int?        get() = null
    val repeatDirection: String?   get() = null
    val repeatedByRow: Boolean?    get() = null
    val maxPerRow: Int?            get() = null
    val collapsed: Boolean?        get() = null
    val panels: Any?               get() = null
    val soloMode: Boolean?         get() = null
    val targets: List<DataTarget>? get() = null
    val datasource: String?        get() = null
    val thresholds: List<Any>?     get() = null
    val pluginVersion: String?     get() = PluginVersion.GRAFANA
    val snapshotData: Any?         get() = null
    val timeFrom: Any?             get() = null
    val timeShift: Any?            get() = null
    val hideTimeOverride: Any?     get() = null
    val options: Any?              get() = Object()
    val maxDataPoints: Int?        get() = null
    val interval: String?          get() = null
    val description: String?       get() = null
    val links: List<Any>?          get() = emptyList()
    val transparent: Boolean       get() = false
}
