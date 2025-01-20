package com.modeldriven.sysmlv2.apiServiceTest

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import com.modeldriven.sysmlv2.apiService.ApiCrudService
import com.modeldriven.sysmlv2.apiService.GraphConfig
import com.modeldriven.sysmlv2.apiService.RdfServiceRdf4j


fun testBase(){

    val changeText = """[
    {
      "@type": "DataVersion",
      "identity": {"@id":"payload_system_element_id"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Spacecraft System",
        "connectionEnd": [{
          "@type": "ConnectionDefinition",
          "@id": "ConnectionDefinition1"
          },
          {
          "@type": "ConnectionDefinition",
          "@id": "ConnectionDefinition2"
          }]
      }
    },
        {
      "@type": "DataVersion",
      "identity": {"@id":"ConnectionDefinition1"},
      "payload": {
        "@type": "ConnectionDefinition",
        "name":"ConnectionDefinition...1"
      }
    },
    {
      "@type": "DataVersion",
      "identity": {"@id":"ConnectionDefinition2"},
      "payload": {
        "@type": "ConnectionDefinition",
        "name":"ConnectionDefinition...2"
      }
    },
    {
      "@type": "DataVersion",
      "identity": {"@id":"part_definition_id_1"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Payload System"
      }
    }
  ]
"""

    val changeText2 = """[
    {
      "@type": "DataVersion",
      "identity": {"@id":"payload_system_element_id"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Spacecraft System 2",
        "connectionEnd": [{
          "@type": "ConnectionDefinition",
          "@id": "ConnectionDefinition1"
          }]
      }
    },
    {
      "@type": "DataVersion",
      "identity": {"@id":"part_definition_id_1"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Payload System"
      }
    }
  ]"""
    val deleteText= """[
    {
      "@type": "DataVersion",
      "identity": {
          "@type": "DataIdentity",
          "@id": "ConnectionDefinition2"
          }
    }
  ]"""


    println("Test simple commit")
    val modelGraph = GraphConfig("urn", "urn:sysmlv2:", "Model")
    val projectGraph = GraphConfig("Project","https://systemsmodeling.com/SysMlProject/")
    val rdfService = RdfServiceRdf4j( modelGraph, projectGraph)

    val apiService = ApiCrudService(rdfService)
    val jsonChange = Json.parseToJsonElement(changeText).jsonArray


    //    fun createCommit(change: JsonArray, branch: JsonObject?, previousCommits: JsonArray?, project: JsonObject)
    apiService.changeService(jsonChange)
    printGraph(rdfService.rdf4JCon)

    println("\nNow get it all back as JSON - modelgraph")
    var modelObjects  = apiService.getElements(null)
    printObjects(modelObjects)

    println("\nDone")

}

