package org.openmbee.flexo.sysmlv2

import io.ktor.server.application.*
import io.ktor.serialization.gson.*
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.hsts.*
import com.codahale.metrics.Slf4jReporter
import io.ktor.server.metrics.dropwizard.*
import java.util.concurrent.TimeUnit
import io.ktor.server.routing.*
import org.openmbee.flexo.sysmlv2.apis.BranchApi
import org.openmbee.flexo.sysmlv2.apis.CommitApi
import org.openmbee.flexo.sysmlv2.apis.DiffMergeApi
import org.openmbee.flexo.sysmlv2.apis.ElementApi
import org.openmbee.flexo.sysmlv2.apis.MetaApi
import org.openmbee.flexo.sysmlv2.apis.ProjectApi
import org.openmbee.flexo.sysmlv2.apis.QueryApi
import org.openmbee.flexo.sysmlv2.apis.RelationshipApi
import org.openmbee.flexo.sysmlv2.apis.TagApi
import org.openmbee.flexo.sysmlv2.infrastructure.LocalDateTimeAdapter
import org.openmbee.flexo.sysmlv2.infrastructure.OffsetDateTimeAdapter
import java.time.LocalDateTime
import java.time.OffsetDateTime


fun Application.main() {
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
        gson {
            registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter)
            registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter)
        }
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
