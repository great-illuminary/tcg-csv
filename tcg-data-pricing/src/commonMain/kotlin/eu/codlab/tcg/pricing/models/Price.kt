package eu.codlab.tcg.pricing.models

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val productId: Int,
    val lowPrice: Double? = null,
    val midPrice: Double? = null,
    val highPrice: Double? = null,
    val marketPrice: Double? = null,
    val directLowPrice: Double?,
    val subTypeName: String
) {
    fun toPrice2() = Price2(
        id = productId,
        low = lowPrice,
        mid = midPrice,
        high = highPrice,
        market = marketPrice,
        directLow = directLowPrice,
        subTypeName = subTypeName
    )
}


@Serializable
data class Price2(
    val id: Int,
    val low: Double? = null,
    val mid: Double? = null,
    val high: Double? = null,
    val market: Double? = null,
    val directLow: Double?,
    val subTypeName: String
) {
    fun toMonthly() = PriceMonthly(
        id = id,
        low = (0..30).map { this.low },
        mid = (0..30).map { this.mid },
        high = (0..30).map { this.high },
        market = (0..30).map { this.market },
        directLow = (0..30).map { this.directLow },
        subTypeName = subTypeName
    )
}

@Serializable
data class PriceMonthly(
    val id: Int,
    val low: List<Double?> = emptyList(),
    val mid: List<Double?> = emptyList(),
    val high: List<Double?> = emptyList(),
    val market: List<Double?> = emptyList(),
    val directLow: List<Double?> = emptyList(),
    val subTypeName: String
)
