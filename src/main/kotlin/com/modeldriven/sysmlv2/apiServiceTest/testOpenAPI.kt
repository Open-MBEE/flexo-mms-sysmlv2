package com.modeldriven.sysmlv2.apiServiceTest

import com.modeldriven.sysmlv2.apiService.*


fun testOpenAPI(){
    val modelGraph = GraphConfig("Model", "urn:")
    val projectGraph = GraphConfig("Project","https://systemsmodeling.com/SysMlProject/")
    val rdfService = RdfServiceRdf4j( modelGraph, projectGraph)
    val spec = schemaSpecFactory("SysML", rdfService)
    assert( spec.containers.contains("PartDefinition"))
}