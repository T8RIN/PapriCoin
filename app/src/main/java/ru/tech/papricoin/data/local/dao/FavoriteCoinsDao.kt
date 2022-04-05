package ru.tech.papricoin.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.tech.papricoin.data.local.entity.FavoriteCoinEntity

@Dao
interface FavoriteCoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: FavoriteCoinEntity)

    @Delete
    suspend fun removeFavoriteCoin(coin: FavoriteCoinEntity)

    @Query("SELECT * FROM favoritecoinentity WHERE id = :id")
    suspend fun getFavoriteCoin(id: String): FavoriteCoinEntity?

    @Query("SELECT * FROM favoritecoinentity")
    fun getCoins(): Flow<List<FavoriteCoinEntity>>

    @Query("DELETE FROM favoritecoinentity")
    suspend fun deleteCoins()

}