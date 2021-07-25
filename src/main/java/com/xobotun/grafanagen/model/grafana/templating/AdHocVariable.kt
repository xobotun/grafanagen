package com.xobotun.grafanagen.model.grafana.templating

/**
 * Have never used this variable type, dunno what this for and how it works.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/adhoc_variable.ts#L9-L15)
 *
 * Description: "Add key/value filters on the fly."
 */
class AdHocVariable(
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    /** Datasource for the variables. */
    val datasource: String,
    /** TODO: Comb sources for usages. */
    val filters: List<Filter>,
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
) {
    override val type = "adhoc"

    data class Filter(
        /** TODO: maybe, a label? */
        val key: String,
        /** TODO: maybe 'matches/not-matches/matches-regex/...' thing?  */
        val operator: String,
        /** TODO: the value it is being filtered against, for sure. */
        val value: String,
    )
}
