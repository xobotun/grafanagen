package com.xobotun.grafanagen.model.grafana.templating

/**
 * Allows to dynamically change datasource. Looks like a it has a rather niche usage.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/datasource_variable.ts#L15-L26)
 *
 * Description: "Enabled you to dynamically switch the datasource for multiple panels."
 */
class DatasourceVariable(
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    override val query: String,
    override val current: VariableWithChoices.CurrentEntry,
    override val options: List<VariableWithChoices.Entry>,
    override val includeAll: Boolean,
    override val multi: Boolean,
    /** TODO: Figure out */
    val regex: String,
    override val refresh: Int
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices, MultiSelectVariable, RefreshableVariable {
    override val type = "datasource"
}
