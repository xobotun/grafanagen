package com.xobotun.grafanagen.model.grafana.templating

/**
 * A constant, not a variable.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/constant_variable.ts#L10-L17)
 *
 * Description: "Define a hidden constant variable, useful for metric prefixes in dashboards you want to share."
 */
class ConstantVariable(
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    override val query: String,
    override val current: VariableWithChoices.CurrentEntry,
    override val options: List<VariableWithChoices.Entry>
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices {
    override val type = "constant"
}
