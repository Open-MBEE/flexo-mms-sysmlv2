package com.modeldriven.sysmlv2.apiServiceTest

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import com.modeldriven.sysmlv2.apiService.ApiCrudService
import com.modeldriven.sysmlv2.apiService.GraphConfig
import java.io.PrintStream


fun testBase(){

    println("Test simple commit")
    val modelGraph = GraphConfig("Model", "urn:sysmlv2:", "Model")
    val projectGraph = GraphConfig("Project","https://systemsmodeling.com/SysMlProject/")
    val rdfService = RdfServiceFlexo( modelGraph, projectGraph)
    rdfService.exportStream = PrintStream("/temp/logs/testBaseRdf4jV1.txt")
    val rdfConnection = rdfService.getDBConnection()

    val apiService = ApiCrudService(rdfService)
    val jsonChange = Json.parseToJsonElement(changeText).jsonArray


    //    fun createCommit(change: JsonArray, branch: JsonObject?, previousCommits: JsonArray?, project: JsonObject)
    apiService.changeService(jsonChange)
    printGraph(rdfConnection)

    println("\nNow get it all back as JSON - modelgraph")
    var modelObjects  = apiService.getElements(null)
    printObjects(modelObjects)

    println("\nDone")

}

fun main() {

    testBase()

}