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
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import io.ktor.server.application.ApplicationStopped
import org.openmbee.flexo.sysmlv2.apis.*
import org.openmbee.flexo.sysmlv2.models.Error

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
    environment.monitor.subscribe(ApplicationStopped) {
        FlexoHttpClient.close()
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
    install(CORS) {
        // credentials (the Authorization header) may only be shared with
        // configured origins — anyHost()+allowCredentials would let any
        // website replay a logged-in user's session
        allowCredentials = GlobalFlexoConfig.corsAllowedOrigins.isNotEmpty()

        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.ETag)
        allowHeader(HttpHeaders.IfMatch)
        allowHeader(HttpHeaders.IfNoneMatch)
        allowHeader(HttpHeaders.SLUG)

        allowMethod(HttpMethod.Head)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)

        exposeHeader("Accept-Post")
        exposeHeader("Accept-Patch")
        exposeHeader("Accept-Put")
        exposeHeader(HttpHeaders.Allow)
        exposeHeader(HttpHeaders.ETag)
        exposeHeader(HttpHeaders.Date)
        exposeHeader(HttpHeaders.Location)
        exposeHeader(HttpHeaders.Link)
        exposeHeader("Flexo-Mms-Layer-1")

        if (GlobalFlexoConfig.corsAllowedOrigins.isEmpty()) {
            anyHost()
        } else {
            GlobalFlexoConfig.corsAllowedOrigins.forEach { origin ->
                val scheme = origin.substringBefore("://", "")
                val host = origin.substringAfter("://")
                if (scheme.isEmpty()) allowHost(host, listOf("http", "https"))
                else allowHost(host, listOf(scheme))
            }
        }
    }
    install(AutoHeadResponse) // see https://ktor.io/docs/autoheadresponse.html
    install(Compression, ApplicationCompressionConfiguration()) // see https://ktor.io/docs/compression.html
    //install(HSTS, ApplicationHstsConfiguration()) // see https://ktor.io/docs/hsts.html
    install(Resources)
    install(StatusPages) {
        // failures respond with the spec's Error shape so clients can
        // parse them uniformly
        exception<InvalidSysmlSerializationError> { call, cause ->
            call.respond(HttpStatusCode.BadRequest,
                Error(Error.AtType.Error, cause.message ?: "Bad Request"))
        }
        exception<BadRequestException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest,
                Error(Error.AtType.Error, cause.message ?: "Bad Request"))
        }
        exception<NotImplementedError> { call, cause ->
            call.respond(HttpStatusCode.NotImplemented,
                Error(Error.AtType.Error, cause.message ?: "Not Implemented"))
        }
        exception<Throwable> { call, cause ->
            // do not leak internal details (exception messages may contain
            // IRIs, paths, or upstream internals)
            call.application.log.error("unhandled exception", cause)
            call.respond(HttpStatusCode.InternalServerError,
                Error(Error.AtType.Error, "Internal Server Error"))
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
    val basePath: String,
    // origins allowed for CORS ("scheme://host[:port]", space-separated);
    // empty means any host, which browsers only honor without credentials
    val corsAllowedOrigins: List<String> = emptyList()
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
        val defaultTimeout = property("flexo.defaultTimeout")?.getString()?.toLong() ?: 60L
        val auth = property("flexo.auth")?.getString() ?: ""
        val basePath = property("flexo.basePath")?.getString() ?: ""
        val corsAllowedOrigins = property("flexo.corsAllowedOrigins")?.getString()
            ?.split(" ")?.filter { it.isNotBlank() } ?: emptyList()
        return FlexoConfig(protocol, host, port, org, defaultTimeout, auth, basePath, corsAllowedOrigins)
    }
