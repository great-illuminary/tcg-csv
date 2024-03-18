package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val groupId: Int,
    val name: String,
    val abbreviation: String,
    val isSupplemental: Boolean = false,
    val publishedOn: String,
    val modifiedOn: String,
    val categoryId: Int
)
