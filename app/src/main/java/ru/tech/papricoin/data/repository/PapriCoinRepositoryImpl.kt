package ru.tech.papricoin.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.local.dao.CoinCurrencyDao
import ru.tech.papricoin.data.local.dao.CoinDao
import ru.tech.papricoin.data.local.dao.CoinDetailDao
import ru.tech.papricoin.data.local.dao.OverviewDao
import ru.tech.papricoin.data.local.entity.CoinCurrencyEntity
import ru.tech.papricoin.data.remote.api.PapriCoinApi
import ru.tech.papricoin.data.remote.dto.*
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.model.CoinDetail
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class PapriCoinRepositoryImpl @Inject constructor(
    private val api: PapriCoinApi,
    private val coinDao: CoinDao,
    private val overviewDao: OverviewDao,
    private val coinDetailDao: CoinDetailDao,
    private val coinCurrencyDao: CoinCurrencyDao
) : PapriCoinRepository {

    override fun getCoins(): Flow<Action<List<Coin>>> = flow {

        emit(Action.Loading())
        val coins = coinDao.getCoins().map { it.coin }

        try {
            val remoteCoins = api.getCoins()
            emit(
                Action.Success(
                    remoteCoins.map { it.coin }
                )
            )
            coinDao.insertCoins(remoteCoins.map { it.coinEntity })
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            if (coins.isNotEmpty()) emit(Action.Success(coins))
        }
    }

    override fun getCoinById(id: String): Flow<Action<CoinDetail>> = flow {
        emit(Action.Loading())
        val coinDetail = coinDetailDao.getCoinDetail(id)

        try {
            val coin = api.getCoinById(id)
            emit(Action.Success(coin.coinDetail))
            coinDetailDao.insertCoinDetail(coin.coinDetailEntity)
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            coinDetail?.let {
                emit(Action.Success(coinDetail.coinDetail))
            }
        }
    }

    override fun getHistoricalCurrencyForPeriod(
        id: String,
        start: String,
        end: String
    ): Flow<Action<List<CoinCurrency>>> = flow {
        emit(Action.Loading())
        val coinCurrency = coinCurrencyDao.getCoinCurrency(id)

        try {
            val remoteCurrency =
                api.getHistoricalCurrencyForPeriod(id, start, end).map { it.coinCurrency }
            emit(Action.Success(remoteCurrency))
            coinCurrencyDao.insertCoinCurrency(
                CoinCurrencyEntity(
                    id, remoteCurrency
                )
            )
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            coinCurrency?.let {
                emit(Action.Success(coinCurrency.currency))
            }
        }

    }

    override fun getOverview(): Flow<Action<Overview>> = flow {
        emit(Action.Loading())
        val overview = overviewDao.getOverview()

        try {
            val overviewDto = api.getOverview()
            emit(Action.Success(overviewDto.overview))
            overviewDao.insertOverview(overviewDto.overviewEntity)
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            overview?.let {
                emit(Action.Success(overview.overview))
            }
        }
    }
}