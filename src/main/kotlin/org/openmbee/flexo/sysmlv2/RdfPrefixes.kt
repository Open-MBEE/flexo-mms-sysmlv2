package org.openmbee.flexo.sysmlv2

import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.rdf.model.Resource
import org.apache.jena.rdf.model.impl.PropertyImpl
import org.apache.jena.shared.PrefixMapping
import org.apache.jena.shared.impl.PrefixMappingImpl

class PrefixedRdfPropertiesMap(
    val map: Map<Property, Set<RDFNode>>,
    val prefixes: PrefixMapping=PrefixMappingImpl()
): HashMap<Property, Set<RDFNode>>(map) {
    // find objects by prefixed IRI string
    fun at(key: String): Set<RDFNode>? {
        return map[resolveKey(key)]
    }

    // transform the CURIE into an RDF property instance
    protected fun resolveKey(key: String): Property {
        return PropertyImpl(if(key.first() == '>') {
            key.substring(1)
        } else if(key.first() == '<' && key.last() == '>') {
            key.substring(1, -1)
        } else {
            prefixes.expandPrefix(key)
        })
    }
}

fun Set<RDFNode>?.literal(): String? {
    return this?.first()?.asLiteral()?.string
}

fun Set<RDFNode>?.resource(): Resource? {
    return this?.first()?.asResource()
}

fun Resource.outgoing(): PrefixedRdfPropertiesMap {
    val outgoingProperties: MutableMap<Property, MutableSet<RDFNode>> = mutableMapOf()

    for(stmt in this.listProperties()) {
        outgoingProperties.computeIfAbsent(stmt.predicate) { mutableSetOf() }.add(stmt.`object`)
    }

    return PrefixedRdfPropertiesMap(outgoingProperties)
}

