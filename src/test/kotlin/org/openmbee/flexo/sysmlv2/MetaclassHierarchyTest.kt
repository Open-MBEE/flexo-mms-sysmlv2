package org.openmbee.flexo.sysmlv2

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class MetaclassHierarchyTest : StringSpec({
    "the bundled vocabulary graph is on the classpath" {
        MetaclassHierarchy.isAvailable.shouldBeTrue()
    }

    "a metaclass is its own first specialization" {
        MetaclassHierarchy.specializationsOf("PartUsage").first() shouldBe "PartUsage"
    }

    "Feature, Usage and Element reach PartUsage transitively" {
        MetaclassHierarchy.specializationsOf("Feature") shouldContain "PartUsage"
        MetaclassHierarchy.specializationsOf("Usage") shouldContainAll listOf("PartUsage", "ItemUsage", "OccurrenceUsage")
        MetaclassHierarchy.specializationsOf("Element") shouldContainAll listOf("PartUsage", "PartDefinition", "Documentation")
    }

    "Definition does not reach PartUsage" {
        MetaclassHierarchy.specializationsOf("Definition") shouldNotContain "PartUsage"
        MetaclassHierarchy.specializationsOf("Definition") shouldContain "PartDefinition"
    }

    "a name the vocabulary does not know is matched exactly" {
        MetaclassHierarchy.specializationsOf("NoSuchMetaclass") shouldBe setOf("NoSuchMetaclass")
    }
})
