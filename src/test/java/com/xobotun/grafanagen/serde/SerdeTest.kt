package com.xobotun.grafanagen.serde

import com.google.common.collect.MapDifference
import com.google.common.collect.Maps
import com.google.gson.*
import com.xobotun.grafanagen.serde.panel.textPanel1
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import com.google.gson.internal.LinkedTreeMap
import com.xobotun.grafanagen.serde.panel.graphPanel1
import com.xobotun.grafanagen.serde.panel.graphPanel2

class SerdeTest {
    private val gson = Gson()

    /**
     * Test that reads a raw json into a tree, reads in-memory object into a tree
     * and then compares them.
     */
    @TestFactory
    fun testSerialization() = listOf(
        textPanel1,
        graphPanel1,
        graphPanel2,
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
        if (!difference.areEqual()) throw AssertionError(difference.toFineString())
    }

    /**
     * Accesses an internal map so there's no need to call [JsonObject.entrySet] to construct another map from it.
     * Recursively omits null values.
     */
    private fun JsonObject.asMap(): Map<String, Any> = this.let {
        val innerTree = JsonObject::class.java.getDeclaredField("members")
        innerTree.isAccessible = true

        return@let innerTree.get(this) as LinkedTreeMap<String, JsonElement>
    }
        .filterValues { !it.isJsonNull }
        .mapValues { (k, v) -> when (v) {
                is JsonObject -> v.asMap()
                is JsonArray -> v.filterNotNull().map { if (it is JsonObject) it.asMap() else it }
                else -> v
            }
        }

    private fun MapDifference<*, *>.toFineString(): String {
        if (areEqual()) return "Equal\n"

        val result = StringBuilder("Not equal:\n")
        result.append(entriesOnlyOnLeft().toFineString("Only on left (expected): "))
        result.append(entriesOnlyOnRight().toFineString("Only on right (actual): "))
        result.append(entriesDiffering().toFineString("Keys present in both, but values are different (expected, actual): "))

        return result.toString()
    }

    private fun Map<*, *>.toFineString(header: String): String {
        if (isEmpty()) return ""

        val result = StringBuilder("$header\n")
        forEach { (k, v) -> result.append("  $k = $v\n") }

        return result.toString()
    }
}
