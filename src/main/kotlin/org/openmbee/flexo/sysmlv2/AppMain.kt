package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.openmbee.flexo.sysmlv2.apis.*

lateinit var GlobalFlexoConfig: FlexoConfig

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    GlobalFlexoConfig = flexoConfig

    install(DefaultHeaders)
    /*install(DropwizardMetrics) {
        val reporter = Slf4jReporter.forRegistry(registry)
            .outputTo(this@main.log)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build()
        reporter.start(10, TimeUnit.SECONDS)
    }*/
    install(ContentNegotiation) {
        json(Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
        })
        /*gson {
            registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter)
            registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter)
        }*/
        //register(ContentType.Application.Json, GsonConverter())
    }
    install(AutoHeadResponse) // see https://ktor.io/docs/autoheadresponse.html
    install(Compression, ApplicationCompressionConfiguration()) // see https://ktor.io/docs/compression.html
    //install(HSTS, ApplicationHstsConfiguration()) // see https://ktor.io/docs/hsts.html
    install(Resources)
    install(Routing) {
        BranchApi()
        CommitApi()
        DiffMergeApi()
        ElementApi()
        MetaApi()
        ProjectApi()
        QueryApi()
        RelationshipApi()
        TagApi()
    }
}


/**
 * Accesses the environment config property (or null) at the given config key
 */
fun Application.property(key: String): ApplicationConfigValue? {
    return environment.config.propertyOrNull(key)
}

/**
 * Flexo config struct
 */
data class FlexoConfig(
    val protocol: URLProtocol,
    val host: String,
    val port: Int,
    val org: String,
    val defaultTimeout: Long
)

/**
 * Gets the Flexo config object from environment config
 */
val Application.flexoConfig: FlexoConfig
    get() {
        val protocol = URLProtocol.createOrDefault( property("flexo.protocol")?.getString() ?: "http")
        val host = property("flexo.host")?.getString() ?: "localhost"
        val port = property("flexo.port")?.getString()?.toInt() ?: 8080
        val org = property("flexo.org")?.getString() ?: "sysmlv2"
        val defaultTimeout = property("flexo.defaultTimeout")?.getString()?.toLong() ?: 60_000L

        return FlexoConfig(protocol, host, port, org, defaultTimeout)
    }
