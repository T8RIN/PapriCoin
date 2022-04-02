package ru.tech.papricoin.data.remote.dto

import com.squareup.moshi.Json
import ru.tech.papricoin.domain.model.Coin

data class CoinDto(
    val id: String,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "is_new") val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

val CoinDto.coin: Coin
    get() = Coin(
        id = id,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        symbol = symbol,
        iconUrl = "https://static.coinpaprika.com/$type/$id/logo.png"
    )