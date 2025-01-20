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
