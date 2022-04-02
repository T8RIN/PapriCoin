package ru.tech.papricoin.data.repository

import ru.tech.papricoin.data.remote.api.PapriCoinApi
import javax.inject.Inject

class PapriCoinRepositoryImpl @Inject constructor(
    private val api: PapriCoinApi
) {
}