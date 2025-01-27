package com.modeldriven.sysmlv2.apiService

/**
 * A class designed to manage SPARQL graph updates, specifically targeting the RDF model.
 * This class allows the addition and removal of RDF triples & updates, forms a combined SPARQL updates,
 * and retrieves combined SPARQL update strings for execution.
 *
 * @property graphName The name of the RDF graph that will be updated.
 * @property rdfService Service providing the interface to RDF
 */
class RdfUpdater(val gc:GraphConfig, val rdfService: RdfService?    ) {
    val graphStart:String = if (gc.graphName==null) "{" else "{GRAPH <${gc.graphName}> {"
    val graphEnd:String = if (gc.graphName==null) "}" else "}}"
    private val newHeader = "INSERT DATA $graphStart\n"
    private val removeHeader = "DELETE DATA $graphStart\n"
    private var newTriples:String = newHeader
    private var removedTriples:String = removeHeader
    private var updates = ArrayList<String>()


    private var lastAddS:String? = null
    private var continueAddS = false
    private var lastRemoveS:String? = null
    private var continueRemoveS = false
    private var header = ""

    fun addTriple(s:String, p:String, o:String) {
        if (lastAddS==s) {
            this.newTriples += " ;\n  $p $o"
        } else {
            if (continueAddS) {this.newTriples += " .\n" }
            this.newTriples += "$s $p $o"
            lastAddS = s
            continueAddS = true
        }
    }
    fun removeTriple(s:String, p:String, o:String) {
        if (lastRemoveS==s) {
            this.removedTriples += " ;\n  $p $o"
        } else {
            if (continueRemoveS) {this.newTriples += " .\n" }
            this.removedTriples += "$s $p $o"
            lastRemoveS = s
            continueRemoveS = true
        }
    }

    /**
     * Delete all triples for a subject without SysML side effects
     */
    fun deleteObject(s:String) {
        this.updates.add("""DELETE WHERE ${graphStart}$s ?p ?o$graphEnd""")
    }
    fun addSparqlUpdate(sparqlUpdate:String?) {
        if (sparqlUpdate!=null && sparqlUpdate.isNotEmpty()) {
            //this.applyAddRemoveUpdate() // This will make the updates ordered, if that is needed.
            this.updates.add(sparqlUpdate)
        }
    }
    fun getHeader():String {
        if (header.isEmpty()) {
            if (rdfService != null) for (gc in rdfService.rdfGraphs.values) {
                header += "PREFIX ${gc.prefix}: <${gc.baseURI}>\n"
            }
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

    /**
     * Retrieves the current list of updates and clears the internal state.
     * This method applies any pending add or remove updates before returning
     * the current list of updates and resetting the internal state for future operations.
     *
     * @return A list of strings representing the updates.
     */
    fun getUpdateList():List<String> {
        applyAddRemoveUpdate()
        val currentUpdates = this.updates
        this.clear()
        return currentUpdates
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
        if (continueAddS) {
            this.newTriples += " .\n$graphEnd \n"
            this.addSparqlUpdate(this.newTriples)
            this.continueAddS = false
            this.newTriples = newHeader
        }
        if (continueRemoveS) {
            this.removedTriples += " .\n$graphEnd \n"
            this.addSparqlUpdate(this.removedTriples)
            this.continueRemoveS = false
            this.removedTriples = removeHeader
        }

    }
    fun clear() {
        this.updates = ArrayList<String>()
        this.lastAddS = null
        this.newTriples = newHeader
        this.removedTriples = removeHeader
        this.continueAddS = false
        this.continueRemoveS = false
        this.header = ""
    }
}