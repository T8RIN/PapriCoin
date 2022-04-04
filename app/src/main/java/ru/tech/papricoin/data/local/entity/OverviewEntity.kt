package ru.tech.papricoin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tech.papricoin.domain.model.Overview

@Entity
data class OverviewEntity(
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
    val volume_24h_usd: Long,
    @PrimaryKey val id: String = "overview"
) {
    val overview
        get() = Overview(
            dominance = bitcoin_dominance_percentage,
            cryptocurrenciesNumber = cryptocurrencies_number
        )
}