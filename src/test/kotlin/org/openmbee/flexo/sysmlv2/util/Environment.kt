package org.openmbee.flexo.sysmlv2.util

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.testing.*

private val cachedConfig: HoconApplicationConfig by lazy {
    HoconApplicationConfig(ConfigFactory.parseResources("application.test.conf").resolve())
}

/**
 * Load test environment from application.test.conf resource
 */
fun testEnv(): ApplicationConfig = cachedConfig

/**
 * Wrapper around Ktor's testApplication that automatically loads the
 * application.test.conf configuration file. In Ktor 3, testApplication
 * no longer auto-loads modules from config files.
 */
fun testApplication(block: suspend ApplicationTestBuilder.() -> Unit) {
    io.ktor.server.testing.testApplication {
        environment {
            config = cachedConfig
        }
        block()
    }
}
