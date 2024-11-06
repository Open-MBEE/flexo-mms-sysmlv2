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
package org.openapitools.server.models

import org.openapitools.server.models.FeatureMembership
import org.openapitools.server.models.FeaturingAnyOf
import org.openapitools.server.models.Identified
import org.openapitools.server.models.TransitionFeatureKind
import org.openapitools.server.models.TypeFeaturing
import org.openapitools.server.models.VisibilityKind

/**
 * 
 * @param atId 
 * @param atType 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param feature 
 * @param isImplied 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
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
 * @param type 
 * @param featureOfType 
 * @param featuringType 
 * @param owningFeatureOfType 
 * @param memberElement 
 * @param memberElementId 
 * @param memberName 
 * @param memberShortName 
 * @param membershipOwningNamespace 
 * @param ownedMemberElement 
 * @param ownedMemberElementId 
 * @param ownedMemberFeature 
 * @param ownedMemberName 
 * @param ownedMemberShortName 
 * @param owningType 
 * @param visibility 
 * @param ownedMemberParameter 
 * @param ownedSubjectParameter 
 * @param ownedStakeholderParameter 
 * @param ownedActorParameter 
 * @param ownedResultExpression 
 * @param kind 
 * @param ownedConstraint 
 * @param referencedConstraint 
 * @param ownedConcern 
 * @param referencedConcern 
 * @param ownedRequirement 
 * @param verifiedRequirement 
 * @param ownedObjectiveRequirement 
 * @param action 
 * @param transitionFeature 
 * @param ownedRendering 
 * @param referencedRendering 
 */
data class Featuring(
    val atId: java.util.UUID,
    val atType: Featuring.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val feature: Identified,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
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
    val type: Identified,
    val featureOfType: Identified,
    val featuringType: Identified,
    val owningFeatureOfType: Identified,
    val memberElement: Identified,
    val memberElementId: kotlin.String,
    val memberName: kotlin.String,
    val memberShortName: kotlin.String,
    val membershipOwningNamespace: Identified,
    val ownedMemberElement: Identified,
    val ownedMemberElementId: kotlin.String,
    val ownedMemberFeature: Identified,
    val ownedMemberName: kotlin.String,
    val ownedMemberShortName: kotlin.String,
    val owningType: Identified,
    val visibility: VisibilityKind,
    val ownedMemberParameter: Identified,
    val ownedSubjectParameter: Identified,
    val ownedStakeholderParameter: Identified,
    val ownedActorParameter: Identified,
    val ownedResultExpression: Identified,
    val kind: TransitionFeatureKind,
    val ownedConstraint: Identified,
    val referencedConstraint: Identified,
    val ownedConcern: Identified,
    val referencedConcern: Identified,
    val ownedRequirement: Identified,
    val verifiedRequirement: Identified,
    val ownedObjectiveRequirement: Identified,
    val action: Identified,
    val transitionFeature: Identified,
    val ownedRendering: Identified,
    val referencedRendering: Identified
) 
{
    /**
    * 
    * Values: ViewRenderingMembership
    */
    enum class AtType(val value: kotlin.String){
        ViewRenderingMembership("ViewRenderingMembership");
    }
}

