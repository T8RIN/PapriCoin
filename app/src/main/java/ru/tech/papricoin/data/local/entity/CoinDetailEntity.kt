package ru.tech.papricoin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tech.papricoin.data.remote.response.Team
import ru.tech.papricoin.domain.model.CoinDetail

@Entity
data class CoinDetailEntity(
    val description: String,
    @PrimaryKey val id: String,
    val is_active: Boolean,
    val message: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>
) {
    val coinDetail
        get() = CoinDetail(
            id = id,
            isActive = is_active,
            name = name,
            rank = rank,
            symbol = symbol,
            description = description,
            message = message,
            tags = tags,
            team = team,
            iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
        )
}
