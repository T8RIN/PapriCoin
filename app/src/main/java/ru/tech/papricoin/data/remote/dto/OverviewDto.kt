package ru.tech.papricoin.data.remote.dto

import ru.tech.papricoin.data.local.entity.OverviewEntity
import ru.tech.papricoin.domain.model.Overview

data class OverviewDto(
    val bitcoin_dominance_percentage: Double,
    val cryptocurrencies_number: Int,
    val last_updated: Int,
    val market_cap_ath_date: String,
    val market_cap_ath_value: Long,
    val market_cap_change_24h: Double,
    val market_cap_usd: Long,
    val volume_24h_ath_date: String,
    val volume_24h_ath_value: Long,
    val volume_24h_change_24h: Double,
    val volume_24h_percent_from_ath: Int,
    val volume_24h_percent_to_ath: Double,
    val volume_24h_usd: Long
)

val OverviewDto.overview
    get() = Overview(
        dominance = bitcoin_dominance_percentage,
        cryptocurrenciesNumber = cryptocurrencies_number
    )

val OverviewDto.overviewEntity
    get() = OverviewEntity(
        bitcoin_dominance_percentage,
        cryptocurrencies_number,
        last_updated,
        market_cap_ath_date,
        market_cap_ath_value,
        market_cap_change_24h,
        market_cap_usd,
        volume_24h_ath_date,
        volume_24h_ath_value,
        volume_24h_change_24h,
        volume_24h_percent_from_ath,
        volume_24h_percent_to_ath,
        volume_24h_usd
    )