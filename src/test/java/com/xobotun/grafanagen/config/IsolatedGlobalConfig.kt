package com.xobotun.grafanagen.config

import org.junit.jupiter.api.extension.ExtendWith
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

/**
 * A JUnit 5 extension annotation to put on methods and classes
 * to make the actual extension do all the hardlifting work of hiding the config for each test.
 */
@Target(CLASS, FUNCTION)
@Retention(RUNTIME)
@ExtendWith(GlobalConfigIncapsulatingExtension::class)
annotation class IsolatedGlobalConfig
