/**
* Systems Modeling API and Services
* REST/HTTP platform specific model (PSM) for the Systems Modeling API and Services
*
* The version of the OpenAPI document: 1.0
*
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
@file:UseSerializers(UUIDSerializer::class)

package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param general
 * @param isImplied
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param name
 * @param ownedAnnotation
 * @param ownedElement
 * @param ownedRelatedElement
 * @param ownedRelationship
 * @param owner
 * @param owningFeature
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelatedElement
 * @param owningRelationship
 * @param owningType
 * @param qualifiedName
 * @param referencedFeature
 * @param referencingFeature
 * @param relatedElement
 * @param shortName
 * @param source
 * @param specific
 * @param subsettedFeature
 * @param subsettingFeature
 * @param target
 * @param textualRepresentation
 */
@Serializable
data class ReferenceSubsettingRequest(
    @SerialName("@type")
    val atType: ReferenceSubsettingRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val general: Identified? = null,
    val isImplied: kotlin.Boolean? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val name: kotlin.String? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningFeature: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelatedElement: Identified? = null,
    val owningRelationship: Identified? = null,
    val owningType: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val referencedFeature: Identified? = null,
    val referencingFeature: Identified? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val shortName: kotlin.String? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val specific: Identified? = null,
    val subsettedFeature: Identified? = null,
    val subsettingFeature: Identified? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null
)
{
    /**
    *
    * Values: ReferenceSubsetting
    */
    enum class AtType(val value: kotlin.String){
        ReferenceSubsetting("ReferenceSubsetting");
    }
}

