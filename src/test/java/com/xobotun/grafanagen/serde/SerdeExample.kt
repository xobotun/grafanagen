package com.xobotun.grafanagen.serde

import com.google.common.reflect.TypeToken

/**
 * A test exemplar of [type]d [value] and its [raw] json representation.
 * Has [name] for some ease of use in tests.
 */
data class SerdeExample<T>(
    val name: String? = null,
    val type: TypeToken<T>,
    val value: T,
    val raw: String,
) where T : Any {
    companion object {
        /** An inline reified constructor that I hope will work without type erasure. */
        inline fun <reified T> of(name: String, value: T, raw: String) where T : Any = SerdeExample(name, TypeToken.of(T::class.java), value, raw)
    }
}
