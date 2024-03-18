package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class ExtendedData(
    val name: String,
    val displayName: String,
    val value: String
)
