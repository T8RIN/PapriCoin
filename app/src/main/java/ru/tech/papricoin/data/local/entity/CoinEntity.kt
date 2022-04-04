package ru.tech.papricoin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tech.papricoin.domain.model.Coin

@Entity
data class CoinEntity(
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String,
    @PrimaryKey val id: String
) {
    val coin
        get() = Coin(
            id = id,
            isActive = is_active,
            isNew = is_new,
            name = name,
            rank = rank,
            symbol = symbol,
            iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
        )
}
