package com.xobotun.grafanagen.printer.promql

import com.xobotun.grafanagen.model.promql.datatype.DataTypeProvider

/**
 * An interface that takes any promql callsite chain and prints it as a string.
 * Implementations are suggested to be recursive and stateless.
 * But nobody said about there shouldn't be any underlying `StatefulPrinterImpl`.
 *
 * Meant only to visit callsites and decide their representations. Printing literals is their own job.
 */
interface Printer<T> where T: DataTypeProvider {
    fun print(entrypoint: T): String
}
