package ru.tech.papricoin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.tech.papricoin.data.local.entity.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCoins(coin: List<CoinEntity>)

    @Query("SELECT * FROM coinentity")
    suspend fun getCoins(): List<CoinEntity>

}