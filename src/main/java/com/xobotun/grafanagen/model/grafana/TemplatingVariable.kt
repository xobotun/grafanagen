package com.xobotun.grafanagen.model.grafana

/**
 * A variable to be used in queries and such.
 * See [Templating] for documetation examples.
 */
data class TemplatingVariable(
    /** Format to use while fetching all values from datasource, eg: `wildcard`, `glob`, `regex`, `pipe`, etc. TODO: find a more definite list. */
    val allFormat: String,
    /** Shows current selected variable text/value on the dashboard. */
    val current: CurrentEntry,
    /** Datasource for the variables. */
    val datasource: String?,
    /** Whether all value option is available or not */
    val includeAll: Boolean,
    /** Whether multiple values can be selected or not from variable value list. */
    val multi: Boolean,
    /** Format to use while fetching timeseries from datasource. TODO: Looks like [allFormat]. */
    val multiFormat: String,
    /** Name of variable. */
    val name: String,
    /** Array of variable text/value pairs available for selection on dashboard. */
    val options: List<Entry>,
    /** Datasource query used to fetch values for a variable. */
    val query: String,
    /** TODO: Figure the purpose out. */
    val refresh: Boolean,
    /** TODO: Figure the purpose out. */
    val regex: String,
    /** Type of variable, i.e. `custom`, `query` or `interval`. TODO: a more definite list, once again. */
    val type: String,
) {
    /** One of the variables fetched by [query] */
    data class Entry(
        /** Whether is the checkbox is ticked. */
        val selected: Boolean,
        /** Shown variable name. */
        val text: String,
        /** Shown variable value. */
        val value: String,
    )

    /** Text and value shown when the dropdown list is not visible. TODO: not sure how it is generated for multiple selected variables. */
    data class CurrentEntry(
        /** Shown variable name. */
        val text: String,
        /** Shown variable value. */
        val value: String,
    )
}
