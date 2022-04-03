package ru.tech.papricoin.domain.use_case.get_historical_currency

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.coinCurrency
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetHistoricalCurrencyUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(id: String, start: String, end: String): Flow<Action<List<CoinCurrency>>> =
        flow {
            try {
                emit(Action.Loading())
                val currencyDto = repository.getHistoricalCurrencyForPeriod(id, start, end)
                emit(Action.Success(currencyDto.map { it.coinCurrency }))
            } catch (e: Exception) {
                emit(Action.Empty(e.localizedMessage))
            }
        }
}