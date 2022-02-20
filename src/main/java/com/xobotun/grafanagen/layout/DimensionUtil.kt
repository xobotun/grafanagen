package com.xobotun.grafanagen.layout

import com.xobotun.grafanagen.config.getGlobalConfig
import com.xobotun.grafanagen.layout.AdjustmentResult.*

/** Determines if there is a need to adjust width or height of a layout */
enum class AdjustmentResult {
    DOES_NOT_FIT,
    ADJUSTMENT_NEEDED,
    FINE_AS_IS
}

/**
 * A common piece of logic for X and Y dimensions to see if something fits within limits.
 */
fun checkIfAdjustmentNeeded(min: Int, desired: Int, limit: Int) = when {
    limit >= desired -> FINE_AS_IS
    limit < min -> DOES_NOT_FIT
    else /* limit >= min && limit < desired */ -> ADJUSTMENT_NEEDED
}

/**
 * Same as above, but with callbacks.
 */
fun adjustIfNeeded(min: Int, desired: Int, limit: Int,
                   onAjustmentNeeded: () -> Unit,
                   onFineAsIs: () -> Unit = {},
                   onDoesNotFit: () -> Unit = { getGlobalConfig().layoutConfig.onLayoutMisfitHandler(min, desired, limit) },
) {
    when (checkIfAdjustmentNeeded(min, desired, limit)) {
        DOES_NOT_FIT -> onDoesNotFit()
        ADJUSTMENT_NEEDED -> onAjustmentNeeded()
        FINE_AS_IS -> onFineAsIs()
    }
}
