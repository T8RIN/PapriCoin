package ru.tech.papricoin.presentation.coin_detail.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.tech.papricoin.common.constants.Constants.COIN_ID_PARAM
import ru.tech.papricoin.domain.model.CoinCurrency
import ru.tech.papricoin.domain.model.CoinDetail
import ru.tech.papricoin.domain.use_case.favorite_coins.add_favorite_coin.AddFavoriteCoinUseCase
import ru.tech.papricoin.domain.use_case.favorite_coins.check_favorite_coin.CheckFavoriteCoinUseCase
import ru.tech.papricoin.domain.use_case.favorite_coins.remove_favorite_coin.RemoveFavoriteCoinUseCase
import ru.tech.papricoin.domain.use_case.get_coin.GetCoinUseCase
import ru.tech.papricoin.domain.use_case.get_historical_currency.GetHistoricalCurrencyUseCase
import ru.tech.papricoin.presentation.utils.Action
import ru.tech.papricoin.presentation.utils.UIState
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val getHistoricalCurrencyUseCase: GetHistoricalCurrencyUseCase,
    private val checkFavoriteCoinUseCase: CheckFavoriteCoinUseCase,
    private val addFavoriteCoinUseCase: AddFavoriteCoinUseCase,
    private val removeFavoriteCoinUseCase: RemoveFavoriteCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinDetailState = mutableStateOf<UIState<CoinDetail?>>(UIState.Empty())
    val coinDetailState: State<UIState<CoinDetail?>> = _coinDetailState

    private val _currencyHistoryState = mutableStateOf<UIState<List<CoinCurrency>>>(UIState.Empty())
    val currencyHistoryState: State<UIState<List<CoinCurrency>>> = _currencyHistoryState

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    private var id = ""


    init {
        savedStateHandle.get<String>(COIN_ID_PARAM)?.let {
            id = it
            reload(id)

            viewModelScope.launch {
                _isFavorite.value = checkFavoriteCoinUseCase.invoke(id)
            }
        }
    }

    private fun getCoin(id: String) {
        getCoinUseCase(id).onEach { result ->
            when (result) {
                is Action.Success -> {
                    _coinDetailState.value = UIState.Success(result.data)
                }
                is Action.Empty -> {
                    _coinDetailState.value = UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    _coinDetailState.value = UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHistoricalCurrency(id: String, start: String, end: String) {
        getHistoricalCurrencyUseCase(id, start, end).onEach { result ->
            when (result) {
                is Action.Success -> {
                    _currencyHistoryState.value = UIState.Success(result.data ?: emptyList())
                }
                is Action.Empty -> {
                    _currencyHistoryState.value = UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    _currencyHistoryState.value = UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun reload(it: String = id) {
        getCoin(it)
        val timestamp = System.currentTimeMillis()
        val month = 24L * 60 * 60 * 31 * 1000 * 11
        val end = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            timestamp
        )
        val start = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            timestamp - month
        )
        getHistoricalCurrency(it, start, end)
    }

    fun processFavorites(id: String) {
        viewModelScope.launch {
            if (_isFavorite.value) removeFavoriteCoinUseCase(id)
            else addFavoriteCoinUseCase(id)

            _isFavorite.value = !_isFavorite.value
        }
    }

}