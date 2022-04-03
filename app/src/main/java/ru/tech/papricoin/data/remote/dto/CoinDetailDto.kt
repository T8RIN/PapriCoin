package ru.tech.papricoin.data.remote.dto

import ru.tech.papricoin.domain.model.CoinDetail

data class CoinDetailDto(
    val description: String?,
    val development_status: String?,
    val first_data_at: String?,
    val hardware_wallet: Boolean?,
    val hash_algorithm: String?,
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val last_data_at: String?,
    val links: Links?,
    val links_extended: List<LinksExtended>?,
    val message: String?,
    val name: String,
    val open_source: Boolean?,
    val org_structure: String?,
    val proof_type: String?,
    val rank: Int,
    val started_at: String?,
    val symbol: String?,
    val tags: List<Tag>?,
    val team: List<Team>?,
    val type: String?,
    val whitepaper: Whitepaper?
)

val CoinDetailDto.coinDetail: CoinDetail
    get() = CoinDetail(
        id = id,
        isActive = is_active,
        name = name,
        rank = rank,
        symbol = symbol ?: "",
        description = description ?: "",
        message = message ?: "",
        tags = tags?.map { it.name } ?: emptyList(),
        team = team ?: emptyList(),
    )