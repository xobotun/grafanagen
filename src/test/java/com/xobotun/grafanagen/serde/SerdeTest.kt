package com.xobotun.grafanagen.serde

import com.google.common.collect.Maps
import com.xobotun.grafanagen.serde.panel.textPanel1
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals
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

            try {
                assertEquals(decomposed, parsed)
            } catch (e: AssertionError) {
                if (!(parsed.isJsonObject && decomposed.isJsonObject)) {
                    throw AssertionError("This test is supposed to compare only objects, but parsed was ${parsed::class.java} and decomposed was ${decomposed::class.java}", e)
                }

                val parsedMap = parsed.asJsonObject.asMap()
                val decomposedMap = decomposed.asJsonObject.asMap()

                val difference = Maps.difference(decomposedMap, parsedMap)
                throw AssertionError(difference.toString(), e)
            }
        }
    }

    /**
     * Accesses an internal map so there's no need to call [JsonObject.entrySet] to construct another map from it.
     */
    private fun JsonObject.asMap() = this.let {
        val innerTree = JsonObject::class.java.getDeclaredField("members")
        innerTree.isAccessible = true

        return@let innerTree.get(this) as LinkedTreeMap<String, JsonElement>
    }
}
