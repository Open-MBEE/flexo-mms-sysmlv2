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
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param annotatedElement
 * @param `annotation`
 * @param body
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param locale
 * @param name
 * @param ownedAnnotatingRelationship
 * @param ownedAnnotation
 * @param ownedElement
 * @param ownedRelationship
 * @param owner
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelationship
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 */
@Serializable
data class CommentAnyOf(
    @SerializedName("@id")
    val atId: java.util.UUID,
    @SerializedName("@type")
    val atType: CommentAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val annotatedElement: kotlin.collections.List<Identified>,
    val `annotation`: kotlin.collections.List<Identified>,
    val body: kotlin.String,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val locale: ActionDefinitionRequestAnyOfDeclaredShortName,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val textualRepresentation: kotlin.collections.List<Identified>
)
{
    /**
    *
    * Values: Comment
    */
    enum class AtType(val value: kotlin.String){
        Comment("Comment");
    }
}

