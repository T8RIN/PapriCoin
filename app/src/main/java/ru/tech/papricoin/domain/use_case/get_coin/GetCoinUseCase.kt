package ru.tech.papricoin.domain.use_case.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.coinDetail
import ru.tech.papricoin.domain.model.CoinDetail
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(id: String): Flow<Action<CoinDetail>> = flow {
        try {
            emit(Action.Loading())
            val coin = repository.getCoinById(id)
            emit(Action.Success(coin.coinDetail))
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
        }
    }
}