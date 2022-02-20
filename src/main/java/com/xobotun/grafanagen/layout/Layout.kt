package com.xobotun.grafanagen.layout

/**
 * Describes minimal and desired dimensions of the elements, as well as their positions.
 *
 * Main purpose of this class is to calculate said dimensions for its children.
 */
interface Layout {
    /** Minimal width this layout agrees to. Measured in units, 1 to 24. See [PanelDimensions] for more info. */
    val minWidth: Int
    /** Minimal height this layout agrees to. Measured in units, 1 to +Inf. See [PanelDimensions] for more info. */
    val minHeight: Int
    /** Maximal and desired width this layout wants. Measured in units, 1 to 24. See [PanelDimensions] for more info. */
    val desiredWidth: Int
    /** Maximal and desired height this layout wants. Measured in units, 1 to +Inf. See [PanelDimensions] for more info. */
    val desiredHeight: Int
    /** Final width this layout will be rendered with. Determined after calling [tryFitInto]. */
    var finalWidth: Int
    /** Final height this layout will be rendered with. Determined after calling [tryFitInto]. */
    var finalHeight: Int

    /**
     * Zero or more layouts this layout will configure dimensions for.
     * Note that this property exists just to represent children of the layout in an iterable way,
     * actual way of how a specific layout handles its contents may greatly vary.
     */
    val children: List<Layout>
    /**
     * Sometimes it is interesting to know who your parent is, right?
     * Actual logic should not rely on this field, though.
     * Nullable because the topmost layout's parent is the dashboard itself.
     */
    val parent: Layout?

    /**
     * Calling this method will propagate to the lowest level of the layout hierarchy
     * in attempt to make all layouts fit within Grafana dashboard.
     */
    fun tryFitInto(widthLimit: Int, heightLimit: Int)

    /**
     * Calling this method after [tryFitInto] will update absolute X and Y coordinates of each panel.
     */
    fun updatePanelCoordinates(layoutTopLeftX: Int, layoutTopLeftY: Int)
}


