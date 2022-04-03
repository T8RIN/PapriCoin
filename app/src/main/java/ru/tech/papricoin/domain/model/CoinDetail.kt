package ru.tech.papricoin.domain.model

import ru.tech.papricoin.data.remote.dto.Team

data class CoinDetail(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val message: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,
)
