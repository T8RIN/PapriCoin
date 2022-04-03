package ru.tech.papricoin.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.coin
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Action<List<Coin>>> = flow {
        try {
            emit(Action.Loading())
            val coins = repository.getCoins()
            emit(
                Action.Success(
                    coins.map { it.coin }
                )
            )
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
        }
    }
}