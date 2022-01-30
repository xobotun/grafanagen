package com.xobotun.grafanagen.printer.promql

/**
 * Wraps the expression in round brackets to prevent me from dealing with
 * operator precedence and other possible ambigious syntax things.
 *
 * Convention is to have everything return a compiling expression.
 * It means that every thing that accepts some expression will know it is compilable and it is not needed to
 * analyze it in any way to determine if brackets are needed.
 *
 * Just slap bracket onto any non-trivial literal expression and you'll be safe, most likely.
 * Will lead to some less readable lisp-like mess in the future, though.
 */
fun String.wrapInBrackets() = "($this)"
