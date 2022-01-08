package com.xobotun.grafanagen.model.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.*
import java.lang.IllegalArgumentException
import kotlin.math.round
import kotlin.reflect.KClass

/**
 * Same as [StringScalar], but with various escaping modes listed in
 * https://prometheus.io/docs/prometheus/2.23/querying/basics/#string-literals
 */
class StringLiteral(
    val value: String,
    val escapeSymbol: Char? = null
): StringScalarProvider, Literal {
    override fun invoke() = StringScalar::class
    override fun print() = if (escapeSymbol == null) value else "$escapeSymbol$value$escapeSymbol"

    /**
     * See also Go escape modes: https://go.dev/ref/spec#String_literals
     * TODO: figure out how ' and " differ. And what specifically ` does not unescape (Duh, officical documentation!)
     */
    enum class EscapeMode(char: Char) {
        /** Treats strings as-is: preserves newlines and does not handle escaped things like `\n` or `\u65e5` */
        RAW('`'),
        /** Just a simple string that handles `\n` like newlines and stuff. Also requires escaping for backslashes: "\\" is `\` */
        INTERPRETED_SINGLE('\''),
        /** Same as [INTERPRETED_SINGLE], I think? */
        INTERPRETED_DOUBLE('\"'),
    }
}
