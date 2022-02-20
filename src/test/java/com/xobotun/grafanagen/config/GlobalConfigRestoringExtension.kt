package com.xobotun.grafanagen.config

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.Extension
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.ConcurrentHashMap

/**
 * An extension to save and restore [GlobalConfig] around each test run.
 */
class GlobalConfigRestoringExtension : BeforeEachCallback, AfterEachCallback {
    private val testToConfigMap = ConcurrentHashMap<String, GlobalConfig>()

    /** Stores old GlobalConfig */
    override fun beforeEach(context: ExtensionContext) {
        testToConfigMap[context.uniqueId] = GLOBAL_CONFIG
    }

    /** Restores old GlobalConfig */
    override fun afterEach(context: ExtensionContext) {
        GLOBAL_CONFIG = testToConfigMap[context.uniqueId]!! // Will fail if JUnit exploded
    }
}
