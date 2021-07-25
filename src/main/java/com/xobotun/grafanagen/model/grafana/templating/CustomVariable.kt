package com.xobotun.grafanagen.model.grafana.templating

/**
 * Displays comma-separated values in a dropdown list that can have one or multiple elements checked.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/custom_variable.ts#L13-L23)
 *
 * Description: "Define variable values manually."
 */
class CustomVariable(
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    /** TODO: looks like this is the source of comma-separated values. */
    override val query: String,
    override val current: VariableWithChoices.CurrentEntry,
    override val options: List<VariableWithChoices.Entry>,
    override val includeAll: Boolean,
    override val multi: Boolean,
    /** TODO: figure out. */
    val allValue: Any?
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices, MultiSelectVariable {
    override val type = "custom"
}
