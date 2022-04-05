package ru.tech.papricoin.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.tech.papricoin.data.local.dao.*
import ru.tech.papricoin.data.local.entity.*

@Database(
    entities = [
        CoinEntity::class,
        OverviewEntity::class,
        CoinDetailEntity::class,
        CoinCurrencyEntity::class,
        FavoriteCoinEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class PapriCoinDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao
    abstract val overviewDao: OverviewDao
    abstract val coinDetailDao: CoinDetailDao
    abstract val coinCurrencyDao: CoinCurrencyDao
    abstract val favoriteCoinsDao: FavoriteCoinsDao

}