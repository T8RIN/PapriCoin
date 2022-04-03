package ru.tech.papricoin.domain.repository

import ru.tech.papricoin.data.remote.dto.CoinCurrencyDto
import ru.tech.papricoin.data.remote.dto.CoinDetailDto
import ru.tech.papricoin.data.remote.dto.CoinDto
import ru.tech.papricoin.data.remote.dto.OverviewDto

interface PapriCoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(id: String): CoinDetailDto

    suspend fun getHistoricalCurrencyForPeriod(
        id: String,
        start: String,
        end: String
    ): List<CoinCurrencyDto>

    suspend fun getOverview(): OverviewDto

}