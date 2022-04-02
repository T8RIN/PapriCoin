package ru.tech.papricoin.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.coin
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Result<List<Coin>>> = flow {
        try {
            val coins = repository.getCoins()
            emit(
                Result.success(
                    coins.map { it.coin }
                )
            )
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}