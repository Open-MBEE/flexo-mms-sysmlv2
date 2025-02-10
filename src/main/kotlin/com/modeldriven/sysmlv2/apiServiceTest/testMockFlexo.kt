package com.modeldriven.sysmlv2.apiServiceTest

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import com.modeldriven.sysmlv2.apiService.ApiCrudService
import com.modeldriven.sysmlv2.apiService.GraphConfig
import com.modeldriven.sysmlv2.apiService.RdfService
import com.modeldriven.sysmlv2.apiService.RdfService.SparQLResultJson
import kotlinx.serialization.json.JsonObject
import java.io.PrintStream


class TestMockFlexo(){
    fun test() {

        println("Test simple commit")
        val modelGraph = GraphConfig("Model", "urn:sysmlv2:")
        val projectGraph = GraphConfig("Project", "urn:sysmlv2project:")
        val rdfService = RdfService(modelGraph, projectGraph)
        rdfService.exportStream = PrintStream("/temp/logs/testMockFlexo.txt") // just for debug
        val apiService = ApiCrudService(rdfService)


        val jsonChange = Json.parseToJsonElement(changeText).jsonArray

        apiService.changeService(jsonChange) // Process the changes

        // Get the SparQL Updates
        rdfService.exportStream.println(modelGraph.updater.getHeader())
        val updates = modelGraph.updater.getUpdateList()
        for (update in updates) {
            // This is where update is sent to Flexo
            println(update)
            rdfService.exportStream.println(update )
        }
        /** Example query **/
        // Get the query string
        val theQuery = rdfService.getByIdQuery(modelGraph, "payload_system_element_id")
        rdfService.exportStream.println("The query: $theQuery")
        /* Now do the query - your way
        * */

        // Now wrap the SparQL JSON in a query result object
        val queryResult = SparQLResultJson(JsonObject(mapOf())) // but do it your way, pass SparQL JSON

        // No use that to produce SysML JSON Objects
        val asSysMlJSON = rdfService.processQueryResultsJson(queryResult)
        println(asSysMlJSON)




        println("\nDone")
    }
}

fun main() {

    TestMockFlexo().test()

}