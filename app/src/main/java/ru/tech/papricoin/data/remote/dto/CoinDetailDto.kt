package ru.tech.papricoin.data.remote.dto

import com.squareup.moshi.Json
import ru.tech.papricoin.domain.model.CoinDetail

data class CoinDetailDto(
    val description: String,
    @Json(name = "development_status") val developmentStatus: String,
    @Json(name = "first_data_at") val firstDataAt: String,
    @Json(name = "hardware_wallet") val hardwareWallet: Boolean,
    @Json(name = "hash_algorithm") val hashAlgorithm: String,
    val id: String,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "last_data_at") val lastDataAt: String,
    val links: Links,
    @Json(name = "links_extended") val linksExtended: List<LinksExtended>,
    val message: String,
    val name: String,
    @Json(name = "open_source") val openSource: Boolean,
    @Json(name = "org_structure") val orgStructure: String,
    @Json(name = "proof_type") val proofType: String,
    val rank: Int,
    @Json(name = "started_at") val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<Team>,
    val type: String,
    val whitepaper: Whitepaper
)

val CoinDetailDto.coinDetail: CoinDetail
    get() = CoinDetail(
        id = id,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        symbol = symbol,
        description = description,
        developmentStatus = developmentStatus,
        firstDataAt = firstDataAt,
        hashAlgorithm = hashAlgorithm,
        lastDataAt = lastDataAt,
        message = message,
        openSource = openSource,
        startedAt = startedAt,
        tags = tags.map { it.name },
        team = team,
        type = type
    )