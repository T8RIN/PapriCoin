package ru.tech.papricoin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tech.papricoin.domain.model.CoinCurrency

@Entity
data class CoinCurrencyEntity(
    @PrimaryKey val id: String,
    val currency: List<CoinCurrency>
)
