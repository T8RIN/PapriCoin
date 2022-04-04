package ru.tech.papricoin.data.remote.response

data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)