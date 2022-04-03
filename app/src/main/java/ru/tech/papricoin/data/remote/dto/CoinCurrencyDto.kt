package ru.tech.papricoin.data.remote.dto

import ru.tech.papricoin.domain.model.CoinCurrency

data class CoinCurrencyDto(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Long,
    val open: Double,
    val time_close: String,
    val time_open: String,
    val volume: Long
)

val CoinCurrencyDto.coinCurrency
    get() = CoinCurrency(
        currency = (low + high) / 2
    )