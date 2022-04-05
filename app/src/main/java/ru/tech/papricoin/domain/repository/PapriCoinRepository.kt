package ru.tech.papricoin.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.model.CoinDetail
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.presentation.utils.Action

interface PapriCoinRepository {

    fun getCoins(): Flow<Action<List<Coin>>>

    fun getCoinById(id: String): Flow<Action<CoinDetail>>

    fun getHistoricalCurrencyForPeriod(
        id: String,
        start: String,
        end: String
    ): Flow<Action<List<CoinCurrency>>>

    fun getOverview(): Flow<Action<Overview>>

    fun getFavoriteCoins(): Flow<Action<List<Coin>>>

    suspend fun checkFavoriteCoin(id: String): Boolean

    suspend fun insertFavoriteCoin(id: String)

    suspend fun removeFavoriteCoin(id: String)

}