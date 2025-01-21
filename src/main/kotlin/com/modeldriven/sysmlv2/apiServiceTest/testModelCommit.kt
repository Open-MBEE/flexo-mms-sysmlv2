package com.modeldriven.sysmlv2.apiServiceTest


import com.modeldriven.sysmlv2.apiService.ApiCrudService
import com.modeldriven.sysmlv2.apiService.GraphConfig
import com.modeldriven.sysmlv2.apiService.RdfServiceRdf4j
import com.modeldriven.sysmlv2.apiService.logApiError
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

import java.io.File
import java.io.PrintStream

val testFiles = listOf("/PartsTreeRedefinition.json")

public class TestModelCommit {
    fun test() {
        println("Model Commit Test")
        //val modelGraph = GraphConfig("Modlel", "urn:", "Model")
        //val projectGraph = GraphConfig("Project", "urn:sysmlproject:")
        val rdfService = RdfServiceRdf4j(modelGraph, projectGraph)
        rdfService.exportStream = PrintStream("/temp/logs/modelCommit.txt")

        val apiProjectService = ApiCrudService(rdfService)
        val modelCon = rdfService.rdf4JCon
        val modelContext = rdfService.getGraphHelper(modelGraph)

        for (fileName in testFiles) {
            println("Test file: '$fileName'")

            val fakeProject = """{
            "@type":"Project",
            "@id":"PRG_${fileName.replace(".json", "")}"
            }"""
            val jsonProject = Json.parseToJsonElement(fakeProject).jsonObject

            val modelFile = this::class.java.getResource(fileName)
            if (modelFile == null) {
                logApiError("Test file not found: '$fileName'")
                return
            }

            val modelStr = modelFile.readText()

            val jsonObj:JsonObject = Json.parseToJsonElement(modelStr).jsonObject

            val prettyJson = Json { prettyPrint = true }
            var prettyJsonStr = prettyJson.encodeToString(jsonObj)

            File("/temp/test"+fileName.replace(".json", "_Pretty.json")).writeText(prettyJsonStr)

            val changes = jsonObj["change"]!!.jsonArray

            //    fun createCommit(change: JsonArray, branch: JsonObject?, previousCommits: JsonArray?, project: JsonObject)
            apiProjectService.changeService(changes)


            println("\nNow get it all back as JSON in test dir- modelgraph")
            val modelObjects = apiProjectService.getElements(null)

            prettyJsonStr = prettyJson.encodeToString(value=modelObjects)
            File("/temp/test"+fileName.replace(".json", "_Output.json")).writeText(prettyJsonStr)
            //printObjects(modelObjects)

        }
        var count = rdfService.reportDefaultedPredicates()
        if (count>0) println("\nMissing predicate count = ${count}")
        //rdfService.reportDefaultedPredicates()
        println("\nModel Test Done")
    }
}
fun main() {

    TestModelCommit().test()

}