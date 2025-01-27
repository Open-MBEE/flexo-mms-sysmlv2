package com.modeldriven.sysmlv2.apiServiceTest

import kotlinx.serialization.json.JsonArray
import org.eclipse.rdf4j.repository.RepositoryConnection
import com.modeldriven.sysmlv2.apiService.GraphConfig

val modelGraph = GraphConfig("Model", "urn:sysmlv2:", "Model")
val projectGraph = GraphConfig("Project","urn:sysmlproject:")

fun printObjects(jsonResult: JsonArray):Int {
    var count = 0
    for (item in jsonResult) {
        println(item)
        count++
    }
    return count
}
fun printGraph(con: RepositoryConnection): Int {
    var count = 0
    var statements = con.getStatements(null, null, null)
    for (statement in statements) {
        println("Statement: $statement")
        count++
    }
    statements.close()
    return count
}

val changeText = """[
    {
      "@type": "DataVersion",
      "identity": {"@id":"payload_system_element_id"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Spacecraft System",
        "connectionEnd": [{
          "@id": "ConnectionDefinition1"
          },
          {
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