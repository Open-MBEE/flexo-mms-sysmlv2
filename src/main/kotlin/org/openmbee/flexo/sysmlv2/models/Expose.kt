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

import org.openmbee.flexo.sysmlv2.models.ExposeAnyOf
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.MembershipExpose
import org.openmbee.flexo.sysmlv2.models.NamespaceExpose
import org.openmbee.flexo.sysmlv2.models.VisibilityKind

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param importOwningNamespace
 * @param importedElement
 * @param isImplied
 * @param isImpliedIncluded
 * @param isImportAll
 * @param isLibraryElement
 * @param isRecursive
 * @param name
 * @param ownedAnnotation
 * @param ownedElement
 * @param ownedRelatedElement
 * @param ownedRelationship
 * @param owner
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelatedElement
 * @param owningRelationship
 * @param qualifiedName
 * @param relatedElement
 * @param shortName
 * @param source
 * @param target
 * @param textualRepresentation
 * @param visibility
 * @param importedMembership
 * @param importedNamespace
 */
@Serializable
data class Expose(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: Expose.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val importOwningNamespace: Identified,
    val importedElement: Identified,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isImportAll: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val isRecursive: kotlin.Boolean,
    val name: kotlin.String,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelatedElement: Identified,
    val owningRelationship: Identified,
    val qualifiedName: kotlin.String,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: kotlin.String,
    val source: kotlin.collections.List<Identified>,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val visibility: VisibilityKind,
    val importedMembership: Identified,
    val importedNamespace: Identified
)
{
    /**
    *
    * Values: NamespaceExpose
    */
    enum class AtType(val value: kotlin.String){
        NamespaceExpose("NamespaceExpose");
    }
}

