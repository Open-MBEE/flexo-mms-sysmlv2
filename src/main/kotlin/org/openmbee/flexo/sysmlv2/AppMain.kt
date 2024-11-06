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
import org.openapitools.server.apis.CommitApi
import org.openapitools.server.apis.DiffMergeApi
import org.openapitools.server.apis.ElementApi
import org.openapitools.server.apis.MetaApi
import org.openapitools.server.apis.ProjectApi
import org.openapitools.server.apis.QueryApi
import org.openapitools.server.apis.RelationshipApi
import org.openapitools.server.apis.TagApi



fun Application.main() {
    install(DefaultHeaders)
    install(DropwizardMetrics) {
        val reporter = Slf4jReporter.forRegistry(registry)
            .outputTo(this@main.log)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build()
        reporter.start(10, TimeUnit.SECONDS)
    }
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter())
    }
    install(AutoHeadResponse) // see https://ktor.io/docs/autoheadresponse.html
    install(Compression, org.openmbee.flexo.sysmlv2.ApplicationCompressionConfiguration()) // see https://ktor.io/docs/compression.html
    install(HSTS, org.openmbee.flexo.sysmlv2.ApplicationHstsConfiguration()) // see https://ktor.io/docs/hsts.html
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
