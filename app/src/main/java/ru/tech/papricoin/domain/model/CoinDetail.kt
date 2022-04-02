package ru.tech.papricoin.domain.model

import ru.tech.papricoin.data.remote.dto.Team

data class CoinDetail(
    val description: String,
    val developmentStatus: String,
    val firstDataAt: String,
    val hashAlgorithm: String,
    val id: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val lastDataAt: String,
    val message: String,
    val name: String,
    val openSource: Boolean,
    val rank: Int,
    val startedAt: String,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,
    val type: String,
)
