package com.xobotun.grafanagen.printer.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.*
import com.xobotun.grafanagen.model.promql.literal.PrometheusFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.SimpleFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.StringLiteral
import com.xobotun.grafanagen.printer.promql.Printer
import java.lang.IllegalArgumentException

/**
 * Supports [OneLinePrinter] in printing complex literals.
 * Must not make recursive calls back to the caller.
 */
object SimpleLiteralPrinter : Printer<DataTypeProvider> {
    override fun print(entrypoint: DataTypeProvider): String {
        return when(entrypoint) {
            // Simple cases responsible for their own logic
            is FloatScalar -> entrypoint.print()
            is SimpleFloatLiteral -> entrypoint.print()
            is PrometheusFloatLiteral -> entrypoint.print()
            is StringScalar -> entrypoint.print()
            is StringLiteral -> entrypoint.print()
            // Cases with branching logic
            is InstantVector -> entrypoint.print()
            is RangeVector -> entrypoint.print()
            else -> throw IllegalArgumentException("Cannot print ${entrypoint::class}, no suitable handler! Entrypoint: $entrypoint")
        }
    }
}
