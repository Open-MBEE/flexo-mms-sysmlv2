package com.modeldriven.sysmlv2.apiService

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.eclipse.rdf4j.model.IRI


import java.util.*

/**
 * RdfService is a service class that provides an interface for managing and interacting
 * with RDF data. It defines RDF-related capabilities to perform
 * CRUD operations on RDF graphs, translate objects to RDF terms, provide Json translations,
 * and manage RDF triples using a specified graph configuration. The root RdfService is
 * intended to be subclassed for specific "back end" stores. If not subclassed,
 * it will accumilate updates in the graphs "Updater" object,
 * In particular, the subclasses shoud provide a QueryHelper and
 * Store changes in the DB.
 * Note that both GraphConfig and PredicateSpec are central to the functions of RdfService
 * ** GraphConfig "configures" a graph to be used and potentially modified.
 * ** PredicateSpec, provided by ShemaSpec, defines the type and of a predicate
 *
 * @ Property modelGraph The graph configuration associated with the model being managed
 * @ Property projectGraph The graph configuration for project data and metadata.
 */
open public class RdfService(modelGraph:GraphConfig, projectGraph:GraphConfig ) {

    // For creating default UUIDs
    var baseUUID:String = UUID.randomUUID().toString()
    var uuidIndex = 0;

    // So far, jst for debug.
    var exportStream = System.out

    // The set of graphs registered for this service
    var rdfGraphs = HashMap<String, GraphConfig>()
    var graphsByPrefix = HashMap<String, GraphConfig>()

    // Some useful contants
    var gcProject = this.addGraph(projectGraph)
    var gcModel = this.addGraph(modelGraph)

    var gcRDF = this.rdfGraphs["RDF"]
    var gcMetamodel = this.rdfGraphs["SysML"]
    var gcUnkown = this.rdfGraphs["UNKOWN"]
    var defaultStringSpec = PredicateSpec("UNDEFINED", "primitive", "string", "SysML", this)
    var rdfTypeSpec = PredicateSpec("type", "primitive", "reference", "rdf", this)
    var defaultedPredicateSpec = PredicateSpec("UNDEFINED", "primitive", "string", "Model", this)


    var rdfSubject: Any // Could be string or DB specific
    var rdfPredicate: Any
    var rdfObject : Any
    var rdfType  : Any
    val uglyJsonEncoder = Json { prettyPrint = false }

    init {
        // These keys are required for these graphs
        projectGraph.defaultedKey("Project", this)
        modelGraph.defaultedKey("Model", this)
        this.addGraph(projectGraph)
        this.addGraph(modelGraph)

        gcMetamodel = this.addGraph("SysML", """https://www.omg.org/spec/SysML/""", "SysML")
        gcRDF = this.addGraph("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
        //this.addGraph("RDFS", "http://www.w3.org/2000/01/rdf-schema#",)
        //this.addGraph("OWL", "http://www.w3.org/2002/07/owl#")
        //this.addGraph("API", """https://www.omg.org/spec/SysML/API#""") // Defined for API in RDF, not for SysML
        this.addGraph("XSD", "http://www.w3.org/2001/XMLSchema#")

        gcUnkown = this.rdfGraphs["UNKOWN"]
        defaultStringSpec = PredicateSpec("UNDEFINED", "primitive", "string", "SysML", this)
        rdfTypeSpec = PredicateSpec("type", "primitive", "reference", "rdf", this)
        rdfTypeSpec.subjectGraph = "SysML" // Types of elements come from SysML - special case
        defaultedPredicateSpec = PredicateSpec("UNDEFINED", "primitive", "string", "Model", this)
        rdfSubject = this.rdfIRI("rdf", "subject") //("http://www.w3.org/1999/02/22-rdf-syntax-ns#subject")
        rdfPredicate = this.rdfIRI("rdf", "predicate")
        rdfObject = this.rdfIRI("rdf", "object")
        rdfType = this.rdfIRI("rdf", "type")
    }


   open fun addTriple(gc: GraphConfig, subject: String, predicateSpec: PredicateSpec, obj: Any?, index: Int?=null) {
        if (obj != null) {

            val term: Any? = this.getGraphTerm(predicateSpec, obj, gc)
            if (term != null) {

                val s = this.getGraphResourceIRI(subject, gc)
                val p =
                    predicateSpec.getRdfIRI() //as IRI//this.getGraphResourceIRI(predicateSpec.predicate, this.getRdfGraph(predicateSpec.graphKey)!!)

                //this.exportStream.println("Add:  ${s?.toString()}  ${p.toString()}  $term  Index:$index")

                this.rdfAdd(s, p, term, gc)

                /** ToDo: Consider editing list?
                 * if (index != null) {
                    val orderUrl = this.getGraphResourceIRI(
                        "${subject}__${this.simpleTerm(predicateSpec.predicate)}__${
                            index.toString(16).padStart(6, '0')
                        }", gc
                    )
                    this.rdfAdd(orderUrl, rdfSubject, s, gc)
                    this.rdfAdd(orderUrl, rdfPredicate, p, gc)
                    this.rdfAdd(orderUrl, rdfObject, term, gc)
                    //model.add(orderUrl,apiIndex, literal(index), ctx) // Currently using URL index
                }**/
            }
        }
    }

    open fun deleteTriple(gc: GraphConfig, subject: String, predicateSpec: PredicateSpec, obj: Any?, index: Int?) {
        if (obj != null) {

            val term: Any? = this.getGraphTerm(predicateSpec, obj, gc)
            if (term != null) {
                //this.exportStream.println("Add: ${subject.gc.prefix}: ${subject.uri}  ${qualified(predicate, subject.gc)}  $term  Index:$index" )
                val s = this.getGraphResourceIRI(subject, gc)
                val p =
                    predicateSpec.getRdfIRI() as IRI //this.getGraphResourceIRI(predicateSpec.predicate, this.getRdfGraph(predicateSpec.graphKey)!!)

                this.rdfRemove(s, p, term, gc)

                /**if (index != null) {
                    val orderUrl = this.getGraphResourceIRI(
                        "${subject}__${this.simpleTerm(predicateSpec.predicate)}__${
                            index.toString(16).padStart(6, '0')
                        }", gc
                    )
                    this.rdfRemove(orderUrl, rdfSubject, s, gc)
                    this.rdfRemove(orderUrl, rdfPredicate, p, gc)
                    this.rdfRemove(orderUrl, rdfObject, term, gc)
                    //model.remove(orderUrl,apiIndex, literal(index), ctx) // Currently using URL index
                }**/
            }
        }
    }
    /**
     * Deletes all triples from the RDF model that match the specified subject and predicate.
     * If indexing is enabled, it performs an indexed deletion using a SPARQL update query.
     * This method is used for removing a list of associated triples from the RDF graph.
     * (Not that this is used when gc.deleteToEdit is false.
     *
     * @param gc    Graph configuration of graph to modify.
     * @param subject The subject of the triples to delete, represented as an `ObjectCache`.
     * Contains information about the graph configuration and subject resource.
     * @param predicateSpec The predicateSpec of the triples to delete, specified as a string.
     * Represents the relationship of the subject with the objects being deleted.
     */
    fun deletePredicateList(gc: GraphConfig, subject: String, predicateSpec: PredicateSpec) {
        val ctxS = this.rdfIRI(gc.baseURI)
        val sS = this.qualifiedNoPrefix(subject, gc)
        val pS = this.qualifiedNoPrefix(predicateSpec.predicate, predicateSpec.boundGc())
        val annotation = "${gcMetamodel?.baseURI}annotation:json:${predicateSpec.predicate}"

        val deleteQuery = if (predicateSpec.schemaType == "array") """
DELETE {
<$sS> <$pS> ?o .
<$sS> <$annotation> ?a
}
WHERE {
<$sS> <$pS> ?o .
optional{
<$sS> <$annotation> ?a
}
}""" else """
DELETE WHERE {
<$sS> <$pS> ?o .
}
    """
        //println(deleteQuery)
        this.sparqlUpdate(deleteQuery, gc)
    }

    /**
     * Utility function to produce the query string for getting elements my ID
     */
    open fun getByIdQuery(gc: GraphConfig, uniqueID: String? ) : String {
        val graphSpec = if (gc.graphName == null) "" else "graph <${gc.graphName}> {"
        val graphEnd = if (gc.graphName == null) "" else "}"
        val uriStr: String? = if (uniqueID == null) null else this.qualifiedNoPrefix(uniqueID, gc)
        val queryString = if (uriStr == null || uriStr == "") """SELECT ?s ?p ?o ?st ?ot WHERE { 
$graphSpec ?s ?p ?o.
optional {?s rdf:type ?st.}
optional {?o rdf:type ?ot}  $graphEnd 
}
order by ?s ?p """
        else """SELECT ?s ?p ?o ?st ?ot WHERE { 
BIND ($uriStr as ?s)            
$graphSpec?s ?p ?o.
?s rdf:type ?st.
optional {?o rdf:type ?ot} $graphEnd }
order by ?s ?p """

        return queryString
    }

    /**
     * Retrieves a JSON representation of model data based on the given, optional, UniqueID. If no
     * uniqueID is provided, all elements in the graph will be returned.
     * ToDo: Break this up to provide for other query functions.
     *
     * @param gc The graph configuration containing information about the RDF graph, such as the base URI.
     * @param uniqueID An optional string identifier used to filter the query; if null or empty, fetches all data.
     * @return A JsonArray containing the JSON representation of the retrieved data respecting indexing, if any.
     */
    open fun getJsonById(gc: GraphConfig, uniqueID: String?): JsonArray {
        val queryResult = this.sparqlQuery(this.getByIdQuery(gc, uniqueID))
        return this.processQueryResultsJson(queryResult, gc)
    }

    /**
     * Process the result of a query to create Json object representations in SysML API form
     * Query must be of the form: SELECT ?s ?p ?o ?st ?ot
     *  - ?s Subject
     *  - ?p Predicate
     *  - ?o Object
     *  - ?st SubjectType
     *  - ?ot Object Type (Optional)
     *
     * @param queryResult RdfQueryHelper providing the result of the query
     * @param gc Graph config of the grh to query
     * @return JsonArray of JsonObjects returned by the query
     */
    fun processQueryResultsJson(queryResult: QueryResultInterface, gc: GraphConfig=this.gcModel ): JsonArray {
        // Process queryResult
        val elements: MutableList<JsonObject> = mutableListOf()
        var currentProperties: HashMap<String, JsonElement>? = null// = HashMap<String, JsonObject>()
        var predicateSpec: PredicateSpec? = null
        var term: JsonElement
        var lastS: String? = null
        while (queryResult.next()) {
            val valueOfS: String = queryResult.getValueString("s")!!
            val valueOfP: String = queryResult.getValueString("p")!!
            val valueOfO = queryResult.getValueString("o")!!
            val valueOfSt = queryResult.getValueString("st")!! // Type of ?s
            val valueOfOt = queryResult.getValueString("ot") // Optional type of ?o
            //println("getJsonById: S:$valueOfS P:$valueOfP O:$valueOfO ST:$valueOfSt OT:$valueOfOt ")


            if (valueOfP == "rdf:type" || valueOfP == "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
                predicateSpec = this.rdfTypeSpec
            else
                predicateSpec =
                    this.getPredicateSpec(gc, this.simpleTerm(valueOfSt), this.simpleTerm(valueOfP))

            if (predicateSpec == null) { /// Unkown predicate - what to do?
                val valueType: String = if (queryResult.isLiteralValue("o")) "string" else "reference"
                //logApiError("Warning: Predicate not found: ${valueOfSt.stringValue()} ${valueOfP.stringValue()}")
                predicateSpec = this.makeDefaultPredicateSpec(
                    this.simpleTerm(valueOfSt),
                    this.simpleTerm(valueOfP),
                    "primitive",
                    valueType
                )
            }
            if (valueOfS != lastS) {
                if (currentProperties != null) {
                    elements.add(JsonObject(currentProperties))
                }
                currentProperties = HashMap<String, JsonElement>()
                lastS = valueOfS
                //println("getById: $valueOfS $valueOfP $valueOfO $valueOfSt $valueOfOt ")
                currentProperties.put("@id", JsonPrimitive(this.simpleTerm(valueOfS)))
                currentProperties.put("@type", JsonPrimitive(this.simpleTerm(valueOfSt)))
            }

            if (predicateSpec.schemaType == "array") {
                if (valueOfP.contains("annotation:json:")) {
                    // we put the ordered list in json as the value
                    currentProperties?.put(this.simpleTerm(valueOfP), JsonPrimitive(valueOfO))
                } /* else we ignore the detail triples **/
            } else if (predicateSpec!=this.rdfTypeSpec){ // Non array value
                val simpleO: Any? =
                    if (predicateSpec.valueType == "reference") this.simpleTerm(valueOfO) else valueOfO

                term = getJsonTerm(
                    predicateSpec,
                    simpleO!!,
                    if (valueOfOt != null) this.simpleTerm(valueOfOt) else predicateSpec.valueType
                )
                currentProperties!!.put(this.simpleTerm(valueOfP), term)
            }
        }
        if (currentProperties != null)
            elements.add(JsonObject(currentProperties))

        return JsonArray(elements)
    }


    /**
    // Generic functions
    **/

    /**
     * Converts the provided object and type specifications into a JSON representation.
     *
     * @param predicateSpec The `PredicateSpec` instance that contains information about the schema type and value type
     *                 for the object being converted.
     * @param objArg The object to be converted into a JSON element. This can be an instance of various data types,
     *               such as primitive types, strings, or objects implementing specific interfaces.
     * @param objType An optional string representing the object's type, used for constructing JSON-LD terms
     *                with `@type` annotations.
     * @return A `JsonElement` that represents the input object within the context of the given type and schema
     *         specifications. This may include JSON primitives, objects, or arrays, depending on the input data.
     */
    open fun getJsonTerm(predicateSpec: PredicateSpec, obj: Any, objType: String?): JsonElement {
        var term: JsonElement? = null

        if (obj is JsonElement) return obj


        val castType = predicateSpec.valueType
        val schemaKind = predicateSpec.schemaType
        val that = this

        if (schemaKind == "primitive" || schemaKind == "array" || schemaKind == "object") {

            when (castType) {
                "string", "uuid" -> term = JsonPrimitive(obj.toString())
                "integer" -> term = JsonPrimitive(if (obj is Int) obj else obj.toString().toInt())
                "boolean" -> term = JsonPrimitive(obj.toString() == "true")
                "number" -> term = JsonPrimitive(if (obj is Number) obj else obj.toString().toFloatOrNull())
                "date-time" -> term = JsonPrimitive(obj.toString())
                "date" -> term = JsonPrimitive(obj.toString())
                "reference" -> term = buildJsonObject {
                    put("@id", JsonPrimitive(that.simpleTerm(obj.toString()) ))
                    //if (objType!=null)
                    //    put("@type", JsonPrimitive(objType))//(obj as ObjectCache).uriId) )
                }

                else -> {
                    term = JsonPrimitive(obj.toString())
                    logApiError("Warning: Qualified type not found: $castType for ${obj.toString()}")
                }
            }

        }

    if (term == null) {
        logApiError("Type not found: $castType for ${obj.toString()}")
        term = JsonPrimitive("ERROR, no binding for: <${obj.toString()}> as a $castType")
    }

    return term
}
    /**
     * Retrieves a `PredicateSpec` that describes the expected structure or type of data associated with the given type class name and predicate.
     * Depending on the predicate and the graph's metamodel specification, the method either returns a default `PredicateSpec`,
     * a special `PredicateSpec` for RDF:type, or retrieves a schema-specific `PredicateSpec`.
     *
     * @param gc The `GraphConfig` containing the graph's configuration details, including the metamodel specification.
     * @param typeName The name of the type to look up, used to determine the `PredicateSpec`.
     * @param predicate The predicate that determines the relationship or property of the type. Special handling is applied
     *                  if the predicate is "rdf:type" or matches the RDF syntax type.
     * @return The `PredicateSpec` object describing the relevant type and value information based on the inputs.
     *         If no specific schema type is found, a default `PredicateSpec` is returned.
     */
    fun getPredicateSpec(gc: GraphConfig, typeName: String, predicate: String): PredicateSpec? {
        var predicateSpec: PredicateSpec? = null
        val schemaSpec = gc.metamodelSpec
        if (predicate == "rdf:type" || predicate == "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            predicateSpec = this.rdfTypeSpec
        else if (schemaSpec != null) {
            predicateSpec = schemaSpec.getPredicateSpec(this.simpleTerm(typeName), this.simpleTerm(predicate))
        }
        return predicateSpec
    }
    val unkownPredicates = HashMap<Pair<String?,String>, PredicateSpec>()

    fun makeDefaultPredicateSpec(typeName:String?, predicateName:String, schemaType:String="primitive", valueType:String="string"):PredicateSpec {
        val predicateSpec = unkownPredicates.get(Pair(typeName,predicateName))
        if (predicateSpec==null) {
            //logApiError("Warning: Predicate <$predicateName> not known in <$typeName> - defaults used")
            val newPredicateSpec = PredicateSpec(predicateName, schemaType, valueType, this.gcMetamodel!!.prefix, this)
            unkownPredicates.put(Pair(typeName,predicateName), newPredicateSpec)
            return newPredicateSpec
        } else {
            return predicateSpec
        }
    }
    open fun reportDefaultedPredicates():Int {
        var count = 0
        for (unkown in unkownPredicates) {
            count++
            logApiError("Warning: Predicate ${unkown.key.second} not known in ${unkown.key.first}")
        }
        return count
    }

   /**
     * Gets an arbitrary UUID for objects where an an @id is not provided. As a convention, the same "base" is
     * used for all UUIDs assigned within a transaction and an index appended.
     */
    fun getUniqueUUID():String {
        this.uuidIndex += 1
        return "${this.baseUUID}_${this.uuidIndex}"
    }

    /**
     * Create & Register a graph for use by this RdfService.
     */
    open fun addGraph(prefix:String, baseURI:String, keyArg:String?=null):GraphConfig {
        val gc = GraphConfig(prefix, baseURI, keyArg, rdfService=this)
        this.rdfGraphs[gc.key] = gc
        this.graphsByPrefix[gc.prefix] = gc
        return gc
    }
    /**
     * Register a graph for use by this RdfService.
     */
    open fun addGraph(gc:GraphConfig):GraphConfig {

        this.rdfGraphs[gc.key] = gc
        this.graphsByPrefix[gc.prefix] = gc
        return gc
    }

    /**
     * Get the graph by kay for this RdfService
     *  @param graphKey The "key" for the graph, where the key is known to the RdfService.
     *  @return The graph configuration, represented as a `GraphConfig` instance.
     */
    fun getRdfGraph(graphKey:String):GraphConfig? {return this.rdfGraphs[graphKey]}

    private fun setBaseUUID(anID:String=this.getUniqueUUID()):String {
        if (anID!=this.baseUUID) {
            this.uuidIndex  = 0
            this.baseUUID = anID
        }
        return anID
    }



    /**
     * Resolves the qualified name or URI for a given resource based on the provided GraphConfig and optional metadata flag.
     *
     * @param resource The resource to be qualified. Can be of type ObjectCache, JsonPrimitive, or any primitive.
     * @param gc The GraphConfig object providing prefix or metamodel specifications for qualification. Can be null.
     * @param isMeta A boolean flag indicating if the qualified name should use the metamodel prefix. Defaults to false.
     * @return The qualified name or URI as a String, or null if the resource is null.
     */
    open fun qualified(resourceArg:Any?, gc: GraphConfig?, isMeta:Boolean=false):String? {
        if (resourceArg!=null) {
            var resource = resourceArg
            if (resource is JsonPrimitive)
                resource = resource.content
            if (resource is String) {
                if (":" in resource) {
                    return resource
                } else {
                    if (gc!=null) {
                        return if (isMeta) "${gc.metamodelSpec?.graphKey}:$resource" else "${gc.prefix}:$resource"
                    } else {
                        //logApiError("Prefix not found for: $resource")
                        return "urn:$resource" // default for unknown
                    }
                }
            } else return resource.toString()
        }

        return null
    }

    /**
     * Resolves the fully qualified name or URI for a given resource based on the provided GraphConfig and optional metadata flag.
     *
     * @param resource The resource to be qualified. Can be of type ObjectCache, JsonPrimitive, or any primitive.
     * @param gc The GraphConfig object providing prefix or metamodel specifications for qualification. Can be null.
     * @param isMeta A boolean flag indicating if the qualified name should use the metamodel prefix. Defaults to false.
     * @return The qualified name or URI as a String, or null if the resource is null.
     */
    open fun qualifiedNoPrefix(resourceArg:Any?, gc: GraphConfig?, isMeta:Boolean=false):String? {
        if (resourceArg!=null) {
            var resource = resourceArg
            if (resource is JsonPrimitive)
                resource = resource.content
            if (resource is String) {
                val index = resource.indexOf(':')
                if (index>0) {
                    val prefix = resource.substring(0, index)
                    val suffix = resource.substring(index+1)
                    return "<${this.rdfGraphs[prefix]}$suffix>"
                } else {
                    if (gc!=null) {
                        return if (isMeta) "<${this.rdfGraphs[gc.metamodelSpec?.graphKey]?.baseURI}$resource>" else "<${gc.baseURI}$resource>"
                    } else {
                        //logApiError("Prefix not found for: $resource")
                        return "<urn:$resource>" // default for unknown
                    }
                }
            } else return resource.toString()
        }

        return null
    }

    /**
     * Resolves an RDF term based on the provided graph configuration, type, predicate, and object argument.
     * This function retrieves or converts an object into an RDF-compatible representation, ensuring
     * type consistency according to the graph's schema specifications. A term is the "object" part
     * on an RDF triple. The term is representation as a string, specializations may return DB
     * specific IRIs or Literals.
     *
     * @param gc The graph configuration used to resolve the RDF term.
     * @param typeName The name of the type/container associated with the RDF term.
     * @param predicate The predicate (property) associated with the RDF term.
     * @param objArg The object argument that needs to be resolved or converted into an RDF term.
     * @return The resolved or converted RDF term, ensuring compatibility with the graph's schema.
     */

    fun getValueAsString(predicateSpec:PredicateSpec, obj:Any?):String? {

        var term:String? =
            if (obj is JsonPrimitive) obj.content
            else if (obj is JsonObject) obj.get("@id")?.jsonPrimitive?.content
            else if (obj==null) null else obj.toString()

        return term
    }

    /**
     * Return the unqualified UUID based on a URI. The UUID is at the end of the URI.
     */
    fun simpleTerm(term:String):String {
        // De-qualify something
        val term2 = term.substringAfterLast(':')
        val term3 = term2.substringAfterLast('#')
        return  term3.substringAfterLast("/")
    }

    /**
     * RDF Library to override
     */

    /**
     * Resolves and returns an RDF `IRI` (Internationalized Resource Identifier) based on the provided object
     * and the graph configuration. The method supports various data types for the input object and handles
     * scenarios like extracting prefixes, handling cached objects, resolving JSON primitives, and returning
     * the input if it's already an IRI.
     *
     * @param obj The object to be converted into an RDF `IRI`. This parameter can be a `String`, `ObjectCache`, `JsonPrimitive`,
     *            or an existing `IRI`. If none of these types match, the object is converted to its string representation.
     * @param gc The graph configuration (`GraphConfig`) that provides context for the operation. This includes
     *           details like the base URI and graph prefixes used to resolve the `IRI`.
     * @return An RDF `IRI` that represents the provided object within the context of the graph configuration.
     */
    open fun getGraphResourceIRI(obj:String?, gc:GraphConfig):Any? {
        return if (obj != null && obj.contains(':')) obj else {
            var iriStr = this.qualified(obj, gc)
            //if (!gc.baseURI.endsWith(":")) iriStr = "<$iriStr>"
            iriStr
        }
    }

    /**
     * The IRI can be DB specific or a string, this returns it only as a string for use in queries.
     * The DSB specific will need to override this
     */
    open fun getGraphResourceString(obj:Any?, gc: GraphConfig):String?{
        return this.qualified(obj, gc)
    }

    open fun encodeRdfString(str:String):String {
        return "'''${str.replace("\'", "\\\'")}'''"
    }

    open fun addValue(gc:GraphConfig, subject:String, predicateSpec:PredicateSpec, obj:Any?){
        if (obj!=null) {

            if (predicateSpec.schemaType=="array") {
                var index = 0
                if (obj is JsonArray) {
                    /*if (obj.isNotEmpty()){ */ // Should we propagate empty lists? May have to augment ParseSchema
                        this.rdfAdd(
                            rdfIRI(qualified(subject, gc)),
                            this.rdfIRI("${gcMetamodel?.prefix}:annotation:json:${predicateSpec.predicate}"),
                            this.getGraphTerm(defaultStringSpec, "${uglyJsonEncoder.encodeToString(obj)}", gc),
                            gc
                        )
                        for (element in obj) {
                            this.addTriple(gc, subject, predicateSpec, element, index++) // Index currently ignored
                        }

                } else {
                    logApiError("Error: Array value for ${predicateSpec.predicate} is not a list: $obj")
                }
            } else {
                this.addTriple(gc, subject, predicateSpec, obj,null )
            }
        }
    }
    /**
     * Deletes a triple from the RDF model based on the specified subject, predicate, and object values.
     * If the object is not null, it resolves the RDF term associated with the object and removes the
     * corresponding triple from the model. Additionally, if indexing is enabled, a SPARQL update query
     * is used for deletion of th eindex.
     *
     * @param subject The subject of the triple, represented as an `ObjectCache`. Contains information
     * about the graph configuration, object type, and RDF properties.
     * @param predicate The predicate of the triple, specified as a string. Represents the relationship
     * between the subject and the object.
     * @param obj The object of the triple, which can be any value (e.g., a string, number, or another object).
     * If null, no action is performed.
     * @param indexed A flag indicating whether indexed deletion should be performed. If true, a SPARQL query
     * will be used to remove the indexed representation of the triple. If false, the triple is removed
     * directly from the model without indexing.
     */


    open fun deleteValue(gc:GraphConfig, subject:String, predicateSpec:PredicateSpec, obj:Any?){
        if (obj!=null) {
            if (predicateSpec.schemaType=="array") {
                this.deletePredicateList(gc, subject, predicateSpec) // Could check for just append or delete
            } else {
                this.deleteTriple(gc, subject, predicateSpec, obj, null)
            }
        }
    }
    /**
     * Deletes an object from the RDF model and related references following data integrity rules.
     * The method constructs and executes a SPARQL query to remove all triples directly or indirectly
     * associated with the specified subject, including references and relationships.
     *
     * @param subject The object to be deleted, represented as an `ObjectCache`. Contains information
     * about the graph configuration, URI, associated data identity, and RDF properties.
     */
    open fun deleteObject(gc: GraphConfig, subject: String?) {
        requireNotNull(subject)
        gc.updater.deleteObject(this.qualifiedNoPrefix(subject,gc)!!)
    }
    /**
     * Deletes an object from the RDF model without deleting references
     * The method constructs and executes a SPARQL query to remove all triples with object as subject
     * This is used for a "replacement" style of edit.
     *
     * @param subject The object to be deleted, represented as an `ObjectCache`. Contains information
     * about the graph configuration, URI, associated data identity, and RDF properties.
     */
    open fun deleteObjectContents(gc: GraphConfig, subject: String?) {
        requireNotNull(subject)
        gc.updater.deleteObject(this.qualifiedNoPrefix(subject,gc)!!)
    }

    /**
     * Low-level RDF graph functions intended to be specialized
     */

    /**
     * Generates a graph term (RDF IRI or Literal) based on the given predicate specification,
     * object, and graph configuration. May be specialized for DB specific IRI & Literals.
     * The default implementation returns terms as Strings but other types may be returned.
     * Note: Still thinking about the best way to handle this variability
     *
     * @param predicateSpec the specification for the predicate that defines the value type
     * @param obj the object whose value will be converted into a graph term
     * @param gc the graph configuration used to resolve certain value types like references
     * @return the generated graph term as a string, or null if the term cannot be resolved
     */
    open fun getGraphTerm( predicateSpec: PredicateSpec, obj:Any?, gc: GraphConfig):Any? {
        var termStr = this.getValueAsString(predicateSpec, obj)
        var term:Any? = termStr
        if (termStr!=null) when (predicateSpec.valueType) {
            "string" ,"String"   -> term = this.encodeRdfString(termStr)//"\"${termStr.replace("\"", "\\\"".replace("\\", "\\\\"))}\""
            "uuid"      ->term = "\"${termStr}\""
            "integer"   -> term = "${termStr}"//^^xsd:integer"
            "boolean" -> term = "${termStr}"//^^xsd:boolean"
            "number" -> term = "${termStr}"//^^xsd:float"
            "date-time" -> term = "${termStr}^^xsd:dateTime"
            "date" -> term = "${termStr}^^xsd:date"
            "reference" -> term = this.getGraphResourceIRI(termStr, gc)
            else -> {
                term = "\"${obj.toString().replace("\"", "\\\"")}\""
                logApiError("Qualified type not found: ${predicateSpec.valueType} for ${obj.toString()}")
            }
        }
        return term
    }

    open fun rdfIRI(base:String, term:String?) :Any {
        requireNotNull(term)
        return if (base.endsWith(':')) "${base}$term" else "${base}:$term"
    }
    open fun rdfIRI(iriStr:String?) :Any {
        requireNotNull(iriStr)
        return iriStr
    }
    open fun rdfAdd(s:Any?, p:Any?, o:Any?, gc:GraphConfig) {
        if (s!=null && p!=null && o!=null)
            gc.updater.addTriple(s as String, p as String, o as String)
        else
            logApiError("ERROR: rdfAdd: null parameter $s $p $o")
    }
    open fun rdfRemove(s:Any?, p:Any?, o:Any?, gc:GraphConfig) {
        if (s!=null && p!=null && o!=null)
            gc.updater.removeTriple(s as String, p as String, o as String)
        else
            logApiError("ERROR: rdfRemove: null parameter $s $p $o")
    }
    open fun sparqlUpdate(query:String, gc:GraphConfig) {
        gc.updater.addSparqlUpdate(query)
    }
    /**
     * Perform a query and return a query helper. Override in DB specific RdfService.
     * Intended to be specialized by subclass.
     */
    open fun sparqlQuery(theQuery:String):QueryResultInterface {return SparQLResultJson(JsonObject(mapOf()))}

    /**
     * Interface representing a stub for RDF query results. This interface provides methods for
     * iterating over query results, accessing specific result values, and determining the types
     * of these values. Intended to be sbclassed for the structure of query results. The SparQL
     * protocol JSON version is: SparQLResulJson
     */
    interface QueryResultInterface {
        /**
         * proceed to next row of query, return true if there are more
         */
        open fun next(): Boolean = false

        /**
         * return true if there are more rows in the query
         */
        open fun hasNext():Boolean = false

        /**
         * Get the named query parameter for the current row
         */
        open fun getValueString(key:String):String? = null

        /**
         * Return true if the named parameter is a literal (not IRI)
         */
        open fun isLiteralValue(key:String):Boolean = false

        /**
         * Get the next row as a JSON object
         */
        open fun nextBindingJson(): JsonObject = JsonObject(HashMap<String, JsonElement>())

        /**
         * Close the query
         */
        open fun close() {}
    }
    /**
     * The `SparQLResulJson` class is a concrete implementation for executing and managing SparQL queries using the
     * SparQL JSON Protocol. It extends the `RdfQueryResultStub` base class, providing actual functionality
     * traverse results, and retrieve query bindings in various formats.
     *
     * This class encapsulates the behavior related to processing query results, ensuring proper handling and
     * closing of resources.
     *
     * @queryResult SparQL JSON Results Object perSparQL Protocol Spec
     */
    class SparQLResultJson(queryResultsSparQlObject:JsonObject) : QueryResultInterface {
        val queryResultArray = ((queryResultsSparQlObject.get("results")?.jsonObject)?.get("bindings") ?: JsonArray(listOf())).jsonArray

        var currentBindingSet: JsonObject? = null
        var index = 0

        override fun next(): Boolean {
            if (index>=queryResultArray.size) return false
            currentBindingSet = queryResultArray.elementAt(index).jsonObject;
            index += 1
            return true
        }
        override fun hasNext():Boolean  {
            val has = index<queryResultArray.size
            if (!has) close() // May not be required here but just in case
            return has
        }
        override fun getValueString(key:String):String? {
            return currentBindingSet?.getValue(key)?.jsonObject?.getValue("value").toString()
        }

        override fun isLiteralValue(key:String):Boolean = (currentBindingSet?.getValue(key)?.jsonObject?.getValue("type")).toString() == "literal"
        override fun nextBindingJson(): JsonObject {
            val elements = HashMap<String, JsonPrimitive>()
            if (this.next()) {
                if (currentBindingSet != null) {
                    for (binding in currentBindingSet!!) {
                        elements[binding.key] = (currentBindingSet?.getValue(binding.key)?.jsonObject?.getValue("value")?.jsonPrimitive) ?: JsonPrimitive("null")
                    }
                }
            } else
                this.close()
            return JsonObject(elements)
        }
        override fun close() {} // Nothing to close here
    }
    /**
     * Starts a transaction in a DB specific way, if the DB handles transactions.
     * @param transactionID : UUID of the transaction, defaults to generating a new one
     * @return transaction ID
     */
    open fun startTransaction(transactionID:String=this.getUniqueUUID()):String {
        if (transactionID!=this.baseUUID) {
            this.exportStream.println("## transaction: $transactionID} \n\n")
            this.setBaseUUID(transactionID) // All generated UUID in a transaction will have the same base

            //for (gc in this.rdfGraphs.values) {
            //    this.exportStream.println("@prefix ${gc.prefix}: <${gc.baseURI}> .")
            //}
        }
        return transactionID
    }

    /**
     * Ends the given transaction in a DB specific way
     */
    open suspend fun endTransaction(transactionID:String?=null){
        println("End transaction: $transactionID} \n\n")
    }

    /**
     * Hook for DB - done updating an object.
     */
    open fun endUpdate(uniqueID:String){}
}
