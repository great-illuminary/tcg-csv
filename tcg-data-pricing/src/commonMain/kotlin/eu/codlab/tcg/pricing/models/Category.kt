package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val categoryId: Int,
    val name: String,
    val modifiedOn: String,
    val displayName: String,
    val seoCategoryName: String,
    val categoryDescription: String,
    val categoryPageTitle: String,
    val sealedLabel: String,
    val nonSealedLabel: String,
    val conditionGuideUrl: String,
    val isScannable: Boolean,
    val popularity: Long,
    val isDirect: Boolean
)
