package ru.tech.papricoin.data.repository

import ru.tech.papricoin.data.remote.api.PapriCoinApi
import ru.tech.papricoin.data.remote.dto.CoinCurrencyDto
import ru.tech.papricoin.data.remote.dto.CoinDetailDto
import ru.tech.papricoin.data.remote.dto.CoinDto
import ru.tech.papricoin.data.remote.dto.OverviewDto
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class PapriCoinRepositoryImpl @Inject constructor(
    private val api: PapriCoinApi
) : PapriCoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetailDto {
        return api.getCoinById(id)
    }

    override suspend fun getCurrencyForCoinById(id: String): CoinCurrencyDto {
        return api.getCurrencyForCoinById(id)
    }

    override suspend fun getHistoricalCurrencyForPeriod(
        id: String,
        start: Long,
        end: Long
    ): List<CoinCurrencyDto> {
        return api.getHistoricalCurrencyForPeriod(id, start, end)
    }

    override suspend fun getOverview(): OverviewDto {
        return api.getOverview()
    }
}