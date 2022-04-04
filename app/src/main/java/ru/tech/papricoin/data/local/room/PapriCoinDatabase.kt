package ru.tech.papricoin.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.tech.papricoin.data.local.dao.CoinCurrencyDao
import ru.tech.papricoin.data.local.dao.CoinDao
import ru.tech.papricoin.data.local.dao.CoinDetailDao
import ru.tech.papricoin.data.local.dao.OverviewDao
import ru.tech.papricoin.data.local.entity.CoinCurrencyEntity
import ru.tech.papricoin.data.local.entity.CoinDetailEntity
import ru.tech.papricoin.data.local.entity.CoinEntity
import ru.tech.papricoin.data.local.entity.OverviewEntity

@Database(
    entities = [CoinEntity::class, OverviewEntity::class, CoinDetailEntity::class, CoinCurrencyEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PapriCoinDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao
    abstract val overviewDao: OverviewDao
    abstract val coinDetailDao: CoinDetailDao
    abstract val coinCurrencyDao: CoinCurrencyDao

}