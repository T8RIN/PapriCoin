package ru.tech.papricoin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.tech.papricoin.data.local.entity.CoinDetailEntity

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)

    @Query("SELECT * FROM coindetailentity WHERE id = :id")
    suspend fun getCoinDetail(id: String): CoinDetailEntity?

    @Query("DELETE FROM coindetailentity")
    suspend fun deleteCoins()

}