package ru.tech.papricoin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.tech.papricoin.data.local.entity.CoinCurrencyEntity

@Dao
interface CoinCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinCurrency(coin: CoinCurrencyEntity)

    @Query("SELECT * FROM coincurrencyentity WHERE id = :id")
    suspend fun getCoinCurrency(id: String): CoinCurrencyEntity?

}