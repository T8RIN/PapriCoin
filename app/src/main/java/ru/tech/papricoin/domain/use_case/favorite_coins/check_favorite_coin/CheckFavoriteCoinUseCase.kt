package ru.tech.papricoin.domain.use_case.favorite_coins.check_favorite_coin

import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class CheckFavoriteCoinUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {

    suspend operator fun invoke(id: String): Boolean {
        return repository.checkFavoriteCoin(id)
    }

}