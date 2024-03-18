package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId: Int,
    val categoryId: Int,
    val groupId: Int,
    val extendedData: List<ExtendedData>
) {
    val number: Int
        get() = extendedData.find { it.name.lowercase() == "number" }
            ?.value
            ?.split("/")
            ?.first()
            ?.trim()
            ?.toIntOrNull() ?: -1
}
