package com.xobotun.grafanagen.config

/**
 * A place to store all the customizable options.
 * I mean, there should be no reason to change these values when generating a single dashboard, right?
 * User will be able to change the data in-between dashboard generation if the need suddenly arises.
 * And no one will demand a parallelable dashboard generation, right?
 * What can go wrong with global variables, anyway?
 * But that's the easiest solution to code down and understand, I think.
 */
var GLOBAL_CONFIG: GlobalConfig = GlobalConfig()

data class GlobalConfig(
    val layoutConfig: LayoutConfig = LayoutConfig.DEFAULT,
)

/**
 * Configuration for all layout-related manipulations.
 */
data class LayoutConfig(
    var onLayoutMisfitHandler: (min: Int, desired: Int, limit: Int) -> Unit,
) {
    companion object {
        val DEFAULT = LayoutConfig(
            onLayoutMisfitHandler = { min, desired, limit -> throw IllegalStateException("Attempted to set layout dimension to $limit when it has minimum of $min and desired size of $desired") }
        )
    }
}
