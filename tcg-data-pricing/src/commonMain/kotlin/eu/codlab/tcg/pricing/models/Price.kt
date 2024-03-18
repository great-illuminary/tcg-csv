package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val productId: Int,
    val lowPrice: Double,
    val midPrice: Double,
    val highPrice: Double,
    val marketPrice: Double,
    val directLowPrice: Double?,
    val subTypeName: String
)
