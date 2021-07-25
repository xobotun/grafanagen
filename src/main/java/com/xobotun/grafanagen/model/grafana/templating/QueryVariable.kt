package com.xobotun.grafanagen.model.grafana.templating

/**
 * Runs a query and fetches some results from it,
 * then displays values in a dropdown list that can have one or multiple elements checked.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/query_variable.ts#L29-L48)
 *
 * Description: "Variable values are fetched from a datasource query."
 */
class QueryVariable (
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    override val query: String,
    override val current: VariableWithChoices.CurrentEntry,
    override val options: List<VariableWithChoices.Entry>,
    override val includeAll: Boolean,
    override val multi: Boolean,
    override val refresh: Int,
    /** TODO: figure out. */
    val regex: String,
    /** Where to fetch data from. */
    val datasource: String,
    /** TODO: Find enum. */
    val sort: Int,
    /** TODO: figure out. Seems like a cached string. */
    val allValue: String,
    /** TODO: figure out. */
    val tags: String,
    /** TODO: figure out. */
    val useTags: Boolean,
    /** TODO: figure out. */
    val tagsQuery: String,
    /** TODO: figure out. */
    val tagValuesQuery: String,
    /** TODO: figure out. */
    val definition: String,
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices, MultiSelectVariable, RefreshableVariable {
    override val type = "query"
}
