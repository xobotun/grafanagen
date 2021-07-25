package com.xobotun.grafanagen.model.grafana.templating

/**
 * Allows to dynamically change sliding window interval on multiple queries.
 *
 * [Sources](https://github.com/grafana/grafana/blob/v6.2.x/public/app/features/templating/interval_variable.ts#L17-L28)
 *
 * Description: "Define a timespan interval (ex 1m, 1h, 1d)."
 */
class IntervalVariable(
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
    val auto: Boolean,
    /** TODO: figure out. */
    val auto_min: String,
    /** TODO: figure out. */
    val auto_count: Int,
) : TemplatingVariable(
    name,
    hide,
    label,
    skipUrlSync
), VariableWithChoices, MultiSelectVariable, RefreshableVariable {
    override val type = "interval"
}
