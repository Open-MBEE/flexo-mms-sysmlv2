package org.openmbee.flexo.sysmlv2

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.apache.jena.vocabulary.RDF
import org.apache.jena.vocabulary.RDFS
import org.slf4j.LoggerFactory

/**
 * The SysML metaclass hierarchy, read from the vocabulary graph bundled as
 * [RESOURCE]: one `rdfs:Class` per metaclass of the OMG pilot metamodel and
 * one `rdfs:subClassOf` per declared general metaclass. Elements carry one
 * `rdf:type`, their concrete metaclass, so a query for an abstract or general
 * metaclass ("Feature", "Usage", "Element") matches only through this graph.
 *
 * The same graph is seeded into the triplestore as the named graph [GRAPH_IRI]
 * for SPARQL consumers that want `rdf:type/rdfs:subClassOf*` directly.
 */
object MetaclassHierarchy {
    const val RESOURCE = "sysml-metaclasses.ttl"
    const val GRAPH_IRI = "urn:sysmlv2:vocabulary:sysml-metaclasses"

    private val logger = LoggerFactory.getLogger(MetaclassHierarchy::class.java)

    // general metaclass name -> the names of its direct specializations;
    // null when the vocabulary graph is not on the classpath
    private val directSpecializations: Map<String, Set<String>>? by lazy { load() }

    /** Whether the vocabulary graph was found; without it [specializationsOf] is the identity. */
    val isAvailable: Boolean get() = directSpecializations != null

    /**
     * The metaclass [type] together with every metaclass that transitively
     * specializes it, [type] first. A name the vocabulary does not know, or
     * any name when the vocabulary is absent, yields just itself, so a query
     * on `@type` then keeps its exact-match meaning.
     */
    fun specializationsOf(type: String): Set<String> {
        val direct = directSpecializations ?: return setOf(type)
        val out = linkedSetOf(type)
        val pending = ArrayDeque(listOf(type))
        while (pending.isNotEmpty()) {
            direct[pending.removeFirst()]?.forEach { sub ->
                if (out.add(sub)) pending.addLast(sub)
            }
        }
        return out
    }

    private fun load(): Map<String, Set<String>>? {
        val stream = MetaclassHierarchy::class.java.classLoader.getResourceAsStream(RESOURCE)
        if (stream == null) {
            logger.warn("$RESOURCE is not on the classpath; @type query constraints match the concrete metaclass only")
            return null
        }
        val model = ModelFactory.createDefaultModel()
        stream.use { RDFDataMgr.read(model, it, Lang.TURTLE) }
        val out = mutableMapOf<String, MutableSet<String>>()
        model.listStatements(null, RDFS.subClassOf, null as RDFNode?).forEach { st ->
            val sub = st.subject.uri ?: return@forEach
            val sup = st.`object`.takeIf { it.isURIResource }?.asResource()?.uri ?: return@forEach
            if (sub.startsWith(SYSMLV2.VOCABULARY) && sup.startsWith(SYSMLV2.VOCABULARY)) {
                out.getOrPut(sup.removePrefix(SYSMLV2.VOCABULARY)) { linkedSetOf() }
                    .add(sub.removePrefix(SYSMLV2.VOCABULARY))
            }
        }
        logger.info("loaded $RESOURCE: ${model.listSubjectsWithProperty(RDF.type, RDFS.Class).toList().size} metaclasses")
        return out
    }
}
