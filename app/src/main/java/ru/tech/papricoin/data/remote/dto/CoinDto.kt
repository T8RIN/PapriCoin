package ru.tech.papricoin.data.remote.dto

import ru.tech.papricoin.data.local.entity.CoinEntity
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

val CoinDto.coinEntity: CoinEntity
    get() = CoinEntity(
        id = id,
        is_active = is_active,
        is_new = is_new,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )
