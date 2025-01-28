package org.openmbee.flexo.sysmlv2

import org.apache.jena.datatypes.BaseDatatype
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource
import org.apache.jena.rdf.model.ResourceFactory
import org.apache.jena.shared.impl.PrefixMappingImpl

val ROOT_CONTEXT = "http://layer1-service"

val DEFAULT_PREFIX_MAPPING = PrefixMappingImpl();
class Static {
    companion object {
        init {
            val prefixes = mutableMapOf(
                "rdf" to "http://www.w3.org/1999/02/22-rdf-syntax-ns#",
                "rdfs" to "http://www.w3.org/2000/01/rdf-schema#",
                "owl" to "http://www.w3.org/2002/07/owl#",
                "xsd" to "http://www.w3.org/2001/XMLSchema#",
                "dct" to "http://purl.org/dc/terms/",
            )

            with("https://mms.openmbee.org/rdf") {
                prefixes.putAll(mapOf(
                    "mms" to "$this/ontology/",
                    "mms-txn" to "$this/ontology/txn.",
                    "mms-object" to "$this/objects/",
                    "mms-datatype" to "$this/datatypes/",
                ))
            }

            with(ROOT_CONTEXT) {
                prefixes.putAll(mapOf(
                    "m" to "$this/",
                    "m-object" to "$this/objects/",
                    "m-graph" to "$this/graphs/",
                    "m-org" to "$this/orgs/",
                    "m-user" to "$this/users/",
                    "m-group" to "$this/groups/",
                    "m-policy" to "$this/policies/",

                    "ma" to "$this/graphs/AccessControl.",
                ))
            }

            DEFAULT_PREFIX_MAPPING.setNsPrefixes(prefixes)
        }
    }
}

object MMS {
    private val BASE = DEFAULT_PREFIX_MAPPING.nsPrefixMap["mms"]!!
    val uri = BASE

    private fun res(id: String): Resource {
        return ResourceFactory.createResource("${BASE}${id}")
    }

    // classes
    val Org = res("Org")
    val Repo = res("Repo")
    val Collection = res("Collection")
    val Snapshot = res("Snapshot")
    val Model = res("Model")
    val Staging = res("Staging")
    val Update = res("Update")
    val Load = res("Load")
    val Commit = res("Commit")
    val Branch = res("Branch")
    val Lock = res("Lock")
    val Diff = res("Diff")
    val Artifact = res("Artifact")

    val User = res("User")
    val Group = res("Group")
    val Policy = res("Policy")

    val Transaction = res("Transaction")


    val RepoMetadataGraph = res("RepoMetadataGraph")
    val SnapshotGraph = res("SnapshotGraph")
    val CollectionMetadataGraph = res("CollectionMetadataGraph")

    // object properties
    val id  = ResourceFactory.createProperty(BASE, "id")

    private fun prop(id: String): Property {
        return ResourceFactory.createProperty(BASE, id)
    }

    // ref/commit properties
    val createdBy = prop("createdBy")

    // transaction properties
    val created = prop("created")
    val serviceId = prop("serviceId")
    val org = prop("org")
    val repo = prop("repo")
    val branch = prop("branch")
    val collection = prop("collection")
    val user = prop("user")
    val completed = prop("completed")
    val requestBody = prop("requestBody")
    val requestPath = prop("requestPath")

    val commitId = prop("commitId")
    val submitted = prop("submitted")
    // access control properties
    val implies = prop("implies")

    val defaultBranchId = prop("defaultBranchId")
    val srcRef = prop("srcRef")
    val dstRef = prop("dstRef")

    val subject = prop("subject")
    val scope = prop("scope")
    val role = prop("role")

    val etag = prop("etag")
    val ref = prop("ref")
    val collects = prop("collects")
    val commit = prop("commit")
    val graph = prop("graph")
    val snapshot = prop("snapshot")
    val parent = prop("parent")
    val data = prop("data")
    val body = prop("body")
    val patch = prop("patch")
    val delete = prop("delete")
    val insert = prop("insert")
    val where = prop("where")

    val contentType = prop("contentType")

    val diffSrc = prop("diffSrc")
    val diffDst = prop("diffDst")

    private val BASE_TXN = "${BASE}txn."
    object TXN {
        private fun prop(id: String): Property {
            return ResourceFactory.createProperty(BASE_TXN, id)
        }

        val stagingGraph = prop("stagingGraph")
        val baseModel = prop("baseModel")
        val baseModelGraph = prop("baseModelGraph")
        val sourceGraph = prop("sourceGraph")

        val diff = prop("diff")
        val commitSource = prop("commitSource")
        val insGraph = prop("insGraph")
        val delGraph = prop("delGraph")
    }
}

object MMS_DATATYPE {
    private val BASE = DEFAULT_PREFIX_MAPPING.nsPrefixMap["mms-datatype"]

    val commitMessage = BaseDatatype("${BASE}commitMessage")
    val sparql = BaseDatatype("${BASE}sparql")
    val sparqlGz = BaseDatatype("${BASE}sparqlGz")
}

object SYSMLV2 {
    val BASE = "urn:sysmlv2:"
    val SYSML = "https://www.omg.org/spec/SysML/"
    val ELEMENT = "${BASE}element:"
    val PROPERTY = "${BASE}property:"
    val RELATION = "${BASE}relation:"
    val ANNOTATION_JSON = "${BASE}annotation:json:"

    fun element(uuid: String): Resource {
        return ResourceFactory.createResource("$BASE$uuid")
    }

    fun prop(id: String): Property {
        return ResourceFactory.createProperty(SYSML, id)
    }

    fun annotation_json(key: String): Property {
        return ResourceFactory.createProperty("$ANNOTATION_JSON:$key")
    }

    fun relation(key: String): Property {
        return ResourceFactory.createProperty("$RELATION:$key")
    }
}
