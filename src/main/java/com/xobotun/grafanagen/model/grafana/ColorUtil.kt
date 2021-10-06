package com.xobotun.grafanagen.model.grafana

object ColorUtil {
    fun rgb(red: Int, green: Int, blue: Int) = "#${red.clamp255().hex()}${green.clamp255().hex()}${blue.clamp255().hex()}"
    fun rgba(red: Int, green: Int, blue: Int, alpha: Double) = "rgba(${red.clamp255()},${green.clamp255()},${blue.clamp255()},${alpha.clamp1()})"

    internal fun Int.clamp255() = when {
        this < 0   -> 0
        this > 255 -> 255
        else       -> this
    }

    internal fun Double.clamp1() = when {
        this < 0 -> 0
        this > 1 -> 1
        else     -> this
    }

    internal fun Int.hex() = toString(16).uppercase()
}
