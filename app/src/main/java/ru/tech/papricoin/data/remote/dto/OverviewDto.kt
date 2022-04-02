package ru.tech.papricoin.data.remote.dto

import com.squareup.moshi.Json
import ru.tech.papricoin.domain.model.Overview

data class OverviewDto(
    @Json(name = "bitcoin_dominance_percentage") val bitcoinDominancePercentage: Double,
    @Json(name = "cryptocurrencies_number") val cryptocurrenciesNumber: Int,
    @Json(name = "last_updated") val lastUpdated: Int,
    @Json(name = "market_cap_ath_date") val marketCapAthDate: String,
    @Json(name = "market_cap_ath_value") val marketCapAthValue: Long,
    @Json(name = "market_cap_change_24h") val marketCapChange24h: Double,
    @Json(name = "market_cap_usd") val marketCapUsd: Long,
    @Json(name = "volume_24h_ath_date") val volume24hAthDate: String,
    @Json(name = "volume_24h_ath_value") val volume24hAthValue: Long,
    @Json(name = "volume_24h_change_24h") val volume24hChange24h: Double,
    @Json(name = "volume_24h_percent_from_ath") val volume24hPercentFromAth: Int,
    @Json(name = "volume_24h_percent_to_ath") val volume24hPercentToAth: Double,
    @Json(name = "volume_24h_usd") val volume24hUsd: Long
)

val OverviewDto.overview
    get() = Overview(
        dominance = bitcoinDominancePercentage,
        cryptocurrenciesNumber = cryptocurrenciesNumber
    )