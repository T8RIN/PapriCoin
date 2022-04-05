package ru.tech.papricoin.domain.use_case.favorite_coins.remove_favorite_coin

import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class RemoveFavoriteCoinUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {

    suspend operator fun invoke(id: String) {
        repository.removeFavoriteCoin(id)
    }

}