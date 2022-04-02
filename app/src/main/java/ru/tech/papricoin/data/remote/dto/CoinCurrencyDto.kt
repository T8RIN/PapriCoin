package ru.tech.papricoin.data.remote.dto

import com.squareup.moshi.Json
import ru.tech.papricoin.domain.model.CoinCurrency
import java.util.*

data class CoinCurrencyDto(
    val close: Double,
    val high: Double,
    val low: Double,
    @Json(name = "market_cap") val marketCap: Long,
    @Json(name = "open") val opened: Double,
    @Json(name = "time_close") val timeClose: String,
    @Json(name = "time_open") val timeOpen: String,
    val volume: Long
)

val CoinCurrencyDto.coinCurrency
    get() = CoinCurrency(
        currency = "%.2f".format((low + high) / 2, Locale.getDefault()).toDouble()
    )