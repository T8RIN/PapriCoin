package ru.tech.papricoin.domain.use_case.get_historical_currency

import kotlinx.coroutines.flow.Flow
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetHistoricalCurrencyUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(id: String, start: String, end: String): Flow<Action<List<CoinCurrency>>> {
        return repository.getHistoricalCurrencyForPeriod(id, start, end)
    }
}