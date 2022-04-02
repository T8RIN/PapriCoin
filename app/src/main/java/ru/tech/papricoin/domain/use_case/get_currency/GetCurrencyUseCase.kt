package ru.tech.papricoin.domain.use_case.get_currency

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.coinCurrency
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(id: String): Flow<Result<CoinCurrency>> = flow {
        try {
            val currencyDto = repository.getCurrencyForCoinById(id)
            emit(Result.success(currencyDto.coinCurrency))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}