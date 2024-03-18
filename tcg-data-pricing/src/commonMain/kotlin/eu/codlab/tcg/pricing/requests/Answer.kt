package eu.codlab.tcg.pricing.requests

import kotlinx.serialization.Serializable

@Serializable
data class Answer<T>(
    val totalItems: Int,
    val success: Boolean,
    val errors: List<String> = emptyList(),
    val results: List<T> = emptyList()
)
