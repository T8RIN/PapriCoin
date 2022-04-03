package ru.tech.papricoin.data.remote.dto

import com.squareup.moshi.Json
import ru.tech.papricoin.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

val CoinDto.coin: Coin
    get() = Coin(
        id = id,
        isActive = is_active,
        isNew = is_new,
        name = name,
        rank = rank,
        symbol = symbol,
        iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
    )