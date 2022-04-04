package ru.tech.papricoin.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Action<List<Coin>>> {
        return repository.getCoins()
    }
}