package com.xobotun.grafanagen.config

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.Extension
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.logging.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

/**
 * An extension to create a separate [GlobalConfig] for each test run.
 *
 * Keeps track of which thread is used to execute a test run and
 * creates a thread-local context for each run.
 *
 * Failed to use ThreadLocal<GlobalConfig> there.
 */
class GlobalConfigIncapsulatingExtension : BeforeEachCallback, AfterEachCallback {
    private val log = LoggerFactory.getLogger(GlobalConfigIncapsulatingExtension::class.java)
    private val testToThreadMap = ConcurrentHashMap<String, Thread>()
    private val threadToConfigMap = ConcurrentHashMap<Thread, GlobalConfig>()

    init {
        val newProvider: GlobalConfigProvider = this::getCurrentThreadGlobalConfig
        log.info { "Replacing $GLOBAL_CONFIG_PROVIDER with $newProvider" }
        GLOBAL_CONFIG_PROVIDER = newProvider
    }

    /** Creates new GlobalConfig */
    override fun beforeEach(context: ExtensionContext) {
        val currentThread = Thread.currentThread()

        testToThreadMap[context.uniqueId] = currentThread
        threadToConfigMap[currentThread] = GlobalConfig()
    }

    /** Frees memory under the no longer needed config */
    override fun afterEach(context: ExtensionContext) {
        val testsThread = testToThreadMap.remove(context.uniqueId)
        if (testsThread == null) {
            log.warn { "No thread found for run #${context.uniqueId}: $context" }
            return
        }
        threadToConfigMap.remove(testsThread)
    }

    fun getCurrentThreadGlobalConfig(): GlobalConfig {
        val currentThread = Thread.currentThread()
        var configCandidate = threadToConfigMap[currentThread]
        if (configCandidate != null) return configCandidate

        log.warn { "No global config found for thread $currentThread, creating new. Will leak" }
        configCandidate = GlobalConfig()
        threadToConfigMap[currentThread] = configCandidate
        return configCandidate
    }
}
