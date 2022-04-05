package ru.tech.papricoin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.tech.papricoin.data.local.entity.OverviewEntity

@Dao
interface OverviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOverview(overview: OverviewEntity)

    @Query("SELECT * FROM overviewentity")
    suspend fun getOverview(): OverviewEntity?

    @Query("DELETE FROM overviewentity")
    suspend fun deleteCoins()

}