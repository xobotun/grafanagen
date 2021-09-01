package com.xobotun.grafanagen.serde

import com.google.common.collect.Maps
import com.xobotun.grafanagen.serde.panel.textPanel1
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.internal.LinkedTreeMap

class SerdeTest {
    private val gson = Gson()

    /**
     * Test that reads a raw json into a tree, reads in-memory object into a tree
     * and then compares them.
     */
    @TestFactory
    fun testSerialization() = listOf(
        textPanel1
    ).map {
        DynamicTest.dynamicTest("${it.name} should serialize correctly") {
            val parsed = JsonParser.parseString(it.raw)
            val decomposed = gson.toJsonTree(it.value, it.type.type)

            assertJsonsEquals(decomposed, parsed)
        }
    }

    /**
     * Runs a custom map comparison of two json objects.
     */
    private fun assertJsonsEquals(expected: JsonElement, actual: JsonElement) {
        if (!(actual.isJsonObject && expected.isJsonObject)) {
            throw AssertionError("This test is supposed to compare only objects, but actual was ${actual::class.java} and expected was ${expected::class.java}")
        }

        val actualMap = actual.asJsonObject.asMap()
        val expectedMap = expected.asJsonObject.asMap()

        val difference = Maps.difference(expectedMap, actualMap)
        throw AssertionError(difference.toString())
    }

    /**
     * Accesses an internal map so there's no need to call [JsonObject.entrySet] to construct another map from it.
     * Omits null values.
     * Treats `transparent` set to `false` as `null`. TODO: move it to some kind of equivalency classes out of this method. It does not belong here.
     */
    private fun JsonObject.asMap() = this.let {
        val innerTree = JsonObject::class.java.getDeclaredField("members")
        innerTree.isAccessible = true

        return@let innerTree.get(this) as LinkedTreeMap<String, JsonElement>
    }
        .filterValues { !it.isJsonNull }
        .toMutableMap()
        .also {
            it.removeKeyIfValueMatches("transparent") { (it?.isJsonPrimitive ?: false) && !it!!.asBoolean }
        }

    private fun <K, V> MutableMap<K, V>.removeKeyIfValueMatches(key: K?, valueMatcher: (V?) -> Boolean) {
        val value = this[key]
        if (valueMatcher(value)) this.remove(key)
    }
}
