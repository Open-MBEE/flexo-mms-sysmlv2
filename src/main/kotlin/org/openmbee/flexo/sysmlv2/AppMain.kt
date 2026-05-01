package org.openmbee.flexo.sysmlv2

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.openmbee.flexo.sysmlv2.apis.*

lateinit var GlobalFlexoConfig: FlexoConfig
lateinit var FlexoHttpClient: HttpClient
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    GlobalFlexoConfig = flexoConfig
    FlexoHttpClient = HttpClient() {
        install(HttpTimeout) {
            requestTimeoutMillis = GlobalFlexoConfig.defaultTimeout * 1000
        }
    }
    install(DefaultHeaders)
    install(CallLogging)
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
            classDiscriminator = "@type"
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
    install(StatusPages) {
        exception<InvalidSysmlSerializationError> { call, cause ->
            call.respondText(cause.message ?: "Bad Request", status = HttpStatusCode.BadRequest)
        }
        exception<BadRequestException> { call, cause ->
            call.respondText(cause.message ?: "Bad Request", status = HttpStatusCode.BadRequest)
        }
        exception<NotImplementedError> { call, cause ->
            call.respondText(cause.message ?: "Not Implemented", status = HttpStatusCode.NotImplemented)
        }
        exception<Throwable> { call, cause ->
            call.respondText(cause.message ?: "Internal Server Error", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route(GlobalFlexoConfig.basePath) {
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
    val defaultTimeout: Long,
    val auth: String,
    val basePath: String
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
        val auth = property("flexo.auth")?.getString() ?: ""
        val basePath = property("flexo.basePath")?.getString() ?: ""
        return FlexoConfig(protocol, host, port, org, defaultTimeout, auth, basePath)
    }
