package com.xobotun.grafanagen.model.grafana.templating

/**
 * A variable to be used in queries and such.
 * See [Templating] for documetation examples.
 */
abstract class TemplatingVariable(
//    /** Format to use while fetching all values from datasource, eg: `wildcard`, `glob`, `regex`, `pipe`, etc. TODO: find a more definite list. */
//    val allFormat: String,
//    /** Format to use while fetching timeseries from datasource. TODO: Looks like [allFormat]. */
//    val multiFormat: String,
    /** Name of variable. */
    val name: String,
    /** Whether to show the variable or not. */
    val hide: HideVariable,
    /** TODO: Find out what this field does. But its default value is empty string. Maybe an user-friendly description? */
    val label: String,
    /** Whether to update http url when the variable was changed or not. */
    val skipUrlSync: Boolean,
) {
    /** Type of the variable that defines fields and behaviour. */
    abstract val type: String
}

interface VariableWithChoices {
    /** Datasource query used to fetch values for a variable. */
    val query: String
    /** Shows current selected variable text/value on the dashboard. */
    val current: CurrentEntry
    /** Array of variable text/value pairs available for selection on dashboard. */
    val options: List<Entry>

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

interface MultiSelectVariable {
    /** Whether "all values" option is available or not. */
    val includeAll: Boolean
    /** Whether multiple values from variable value list can be selected or not. */
    val multi: Boolean
}

interface RefreshableVariable {
    /** TODO: figure out. Can be 1 or 2, maybe more. */
    val refresh: Int
}
