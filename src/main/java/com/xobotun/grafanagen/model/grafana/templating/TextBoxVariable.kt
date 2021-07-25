package com.xobotun.grafanagen.model.grafana.templating

/**
 * Allows entering any arbitrary string. Not sure why this thing exists. Some js quirks?
 * Anyway, seems like a dangerous instrument.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/TextBoxVariable.ts#L10-L17)
 *
 * Description: "Define a textbox variable, where users can enter any arbitrary string."
 */
class TextBoxVariable(
    name: String,
    hide: HideVariable,
    label: String,
    skipUrlSync: Boolean,
    override val query: String,
    override val current: VariableWithChoices.CurrentEntry,
    override val options: List<VariableWithChoices.Entry>,
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices {
    override val type = "textbox"
}
