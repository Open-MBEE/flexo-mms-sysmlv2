package com.modeldriven.sysmlv2.apiServiceTest

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.eclipse.rdf4j.repository.RepositoryConnection
import com.modeldriven.sysmlv2.apiService.ApiCrudService
import com.modeldriven.sysmlv2.apiService.GraphConfig
import com.modeldriven.sysmlv2.apiService.RdfServiceRdf4j
import java.io.PrintStream



fun test1(){

    println("Test simple commit")
    //val modelGraph = GraphConfig("Model", "urn:sysmlv2:", "Model")
    //val projectGraph = GraphConfig("Project","urn:sysmlproject:")
    val rdfService = RdfServiceRdf4j( modelGraph, projectGraph)
    rdfService.exportStream = PrintStream("/temp/logs/test1.txt")


    val apiService = ApiCrudService(rdfService)
    val jsonChange = Json.parseToJsonElement(changeText).jsonArray


    //    fun createCommit(change: JsonArray, branch: JsonObject?, previousCommits: JsonArray?, project: JsonObject)
    apiService.changeService(jsonChange)

    val modelCon = rdfService.rdf4JCon
    val modelContext = rdfService.getGraphHelper(modelGraph)

    println("\nDump graph")
    printGraph(modelCon)

    println("\nNow get it all back as JSON - modelgraph")
    var modelObjects  = apiService.getElements(null)
    printObjects(modelObjects)

    //assert( modelObjects.toString() == "[{\"@type\":\"PartDefinition\",\"name\":\"Spacecraft System\",\"@id\":\"payload_system_element_id\"}]")


    //Edit commit
    val jsonChange2 = Json.parseToJsonElement(changeText2).jsonArray
    apiService.changeService(jsonChange2)

    println("\nNow get edited objects back")
    val modelObjects3  = apiService.getElements("payload_system_element_id")
    printObjects(modelObjects3)





    println("\nNow delete objects")
    val jsonDelete = Json.parseToJsonElement(deleteText).jsonArray
    apiService.changeService(jsonDelete)
    println("\nNow get it all back after delete ")
    val modelObjects4  = apiService.getElements(null)
    var count = printObjects(modelObjects4)
    println( "Object count = ${count}")
    assert( count == 3 )



    println("\nDump graph")
    count = printGraph(modelCon)
    println( "Element count = ${count}")
    assert( count == 10 )

    println("\nMissing predicates: ${rdfService.reportDefaultedPredicates()}")

    println("\nDone")

}
fun main() {

    test1()

}


