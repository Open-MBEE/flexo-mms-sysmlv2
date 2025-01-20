package com.modeldriven.sysmlv2.apiService

/**
 * A class designed to manage SPARQL graph updates, specifically targeting the RDF model.
 * This class allows the addition and removal of RDF triples & updates, forms a combined SPARQL updates,
 * and retrieves combined SPARQL update strings for execution.
 *
 * @property graphName The name of the RDF graph that will be updated.
 * @property rdfService Service providing the interface to RDF
 */
class RdfUpdater(val graphName:String, val rdfService: RdfService?    ) {
    private var newTriples = ArrayList<String>()
    private var removedTriples = ArrayList<String>()
    private var updates = ArrayList<String>()
    private val graphSpec:String = "GRAPH <${graphName}> "

    fun addTriple(s:String, p:String, o:String) {
        this.newTriples.add("$s $p $o .")
    }
    fun removeTriple(s:String, p:String, o:String) {
        this.removedTriples.add("$s $p $o .")
    }
    fun addSparqlUpdate(sparqlUpdate:String?) {
        if (sparqlUpdate!=null && sparqlUpdate.isNotEmpty()) {
            this.applyAddRemoveUpdate() // This will make the updates ordered, if that is needed.
            this.updates.add(sparqlUpdate)
        }
    }
    fun getHeader():String {
        var header = ""
        if (rdfService!=null) for (gc in rdfService.rdfGraphs.values) {
            //if (!(gc.baseURI.endsWith(":"))) // urn: is not a prefix
                header += "PREFIX ${gc.prefix}: <${gc.baseURI}>\n"
        }
        return header
    }
    fun getIntermediateUpdate():String { // Update string without clear, just for debug
        applyAddRemoveUpdate()
        var update = ""
        var first = true

        if (this.updates.isNotEmpty()) {
            for (u in this.updates) {
                if (!first) update += ";\n" else
                {
                    update = this.getHeader()
                    first = false
                }
                update += u
            }
        }
        update += "\n"
        return update
    }
    fun getCombinedUpdate():String {
        val update =  this.getIntermediateUpdate()
        this.clear()
        return update
    }
    fun getFinalUpdate():String {
        return this.getHeader() + this.getCombinedUpdate()
    }

    fun applyAddRemoveUpdate(){
        var update:String = ""
        if (removedTriples.isNotEmpty()) {
            update += "DELETE DATA { $graphSpec {\n"
            for (triple in removedTriples) {
                update += "  ${triple}\n"
            }
            update += "}}"
        }
        if (newTriples.isNotEmpty()) {
            if (update.isNotEmpty()) update += ";"
            update += "INSERT DATA { $graphSpec { \n"
            for (triple in newTriples) {
                update += "  ${triple}\n"
            }
            update += "}}"
        }

        this.newTriples = ArrayList<String>()
        this.removedTriples = ArrayList<String>()
        this.addSparqlUpdate(update)
    }
    fun clear() {
        this.newTriples = ArrayList<String>()
        this.removedTriples = ArrayList<String>()
        this.updates = ArrayList<String>()
    }
}