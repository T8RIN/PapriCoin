package ru.tech.papricoin.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.tech.papricoin.data.remote.dto.CoinCurrencyDto
import ru.tech.papricoin.data.remote.dto.CoinDetailDto
import ru.tech.papricoin.data.remote.dto.CoinDto
import ru.tech.papricoin.data.remote.dto.OverviewDto

interface PapriCoinApi {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String) : CoinDetailDto

    @GET("/v1/coins/{id}/ohlcv/today")
    suspend fun getCurrencyForCoinById(@Path("id") id: String) : CoinCurrencyDto

    @GET("/v1/coins/{id}/ohlcv/historical?start={start}&end={end}")
    suspend fun getHistoricalCurrencyForPeriod(@Path("id") id: String, @Path("start") start: Long, @Path("end") end: Long) : List<CoinCurrencyDto>

    @GET("https://api.coinpaprika.com/v1/global")
    suspend fun getOverview() : OverviewDto

}