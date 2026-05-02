package org.openmbee.flexo.sysmlv2.util

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import org.apache.jena.rdfconnection.RDFConnection
import org.apache.jena.sparql.exec.http.UpdateExecutionHTTP
import java.io.File

/**
 * Base spec for sysmlv2 tests.
 *
 * The tests run the sysmlv2 module in-process via Ktor's `testApplication`
 * (loaded by [testApplication] in `Environment.kt`). The in-process module
 * forwards REST calls to a real layer1-service container started via
 * `src/test/resources/docker-compose.yml` at `http://localhost:8080`.
 *
 * Before each test the triplestore is wiped, the seed `cluster.trig`
 * dataset is reloaded, and the `sysmlv2` org is recreated on layer1.
 * This mirrors the layer1 test pattern in
 * `flexo-mms-layer1-service/src/test/kotlin/.../util/Common.kt`.
 */
open class CommonSpec : StringSpec() {

    private val clusterFilePath: String =
        File(javaClass.classLoader.getResource("cluster.trig")!!.file).absolutePath

    private val updateUrl: String =
        testEnv().property("flexoMmsTest.updateUrl").getString()

    private val gspUrl: String =
        testEnv().property("flexoMmsTest.graphStoreProtocolUrl").getString()

    private val layer1BaseUrl: String by lazy {
        val protocol = testEnv().property("flexo.protocol").getString()
        val host = testEnv().property("flexo.host").getString()
        val port = testEnv().property("flexo.port").getString()
        "$protocol://$host:$port"
    }

    private val sysmlv2Org: String by lazy {
        testEnv().property("flexo.org").getString()
    }

    override suspend fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)

        // 1. drop everything from the triplestore
        UpdateExecutionHTTP.service(updateUrl).update("drop all").execute()

        // 2. reload the seed dataset (cluster.trig holds users, groups,
        //    access-control policies, etc. that layer1 expects)
        RDFConnection.connect(gspUrl).use {
            it.putDataset(clusterFilePath)
        }

        // 3. (re)create the sysmlv2 org on layer1 so the in-process
        //    sysmlv2 service has a parent org to attach project repos to
        HttpClient().use { client ->
            val response = client.put("$layer1BaseUrl/orgs/$sysmlv2Org") {
                header(HttpHeaders.Authorization, authorization())
                header(HttpHeaders.ContentType, "text/turtle")
                setBody("""<> <http://purl.org/dc/terms/title> "$sysmlv2Org"@en .""")
            }
            require(response.status.isSuccess()) {
                "failed to create sysmlv2 org on layer1: ${response.status} ${response.bodyAsText()}"
            }
        }
    }
}
