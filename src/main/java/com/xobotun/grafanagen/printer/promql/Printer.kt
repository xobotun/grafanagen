package com.xobotun.grafanagen.printer.promql

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider

/**
 * An interface that takes any promql callsite chain and prints it as a string.
 * Implementations are suggested to be recursive and stateless.
 * But nobody said about there shouldn't be any underlying `StatefulPrinterImpl`.
 */
interface Printer<T> where T: DataTypeProvider {
    /**
     * Returns true if this current printer is able to process the [entrypoint], false otherwise.
     * You are supposed to use a different printer in the latter case.
     */
    fun canPrint(entrypoint: T): Boolean = true

    /**
     * Attempts to return a string representation of the [entrypoint] given and
     * gives back control to different printers that may be able to handle the entrypoint if this one is unable.
     */
    fun print(entrypoint: T, printers: List<out Printer<in DataTypeProvider>>): String

    companion object {
        /**
         * Finds a suitable printer and attempts to turn [entrypoint] into string with [printers] provided.
         */
        fun print(entrypoint: DataTypeProvider, printers: List<out Printer<in DataTypeProvider>>): String {
            return findSuitablePrinter(entrypoint, printers).print(entrypoint, printers)
        }

        internal fun findSuitablePrinter(entrypoint: DataTypeProvider, printers: List<out Printer<in DataTypeProvider>>): Printer<in DataTypeProvider> {
            return printers.firstOrNull { it.canPrint(entrypoint) } ?: throw IllegalArgumentException("No printer for $entrypoint found among $printers")
        }
    }
}

