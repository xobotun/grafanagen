package com.xobotun.grafanagen.config

/**
 * An entry point to access the global config.
 */
fun getGlobalConfig(): GlobalConfig = GLOBAL_CONFIG_PROVIDER()

/**
 * Global config provider that is supposed to ease testing a bit.
 */
typealias GlobalConfigProvider = () -> GlobalConfig
var GLOBAL_CONFIG_PROVIDER: GlobalConfigProvider = NormalGlobalConfigProvider

/**
 * A single-threaded global config provider. Should be enough for all the normal scenarios.
 */
object NormalGlobalConfigProvider : GlobalConfigProvider {
    private val INSTANCE = GlobalConfig()
    override fun invoke() = INSTANCE
}

