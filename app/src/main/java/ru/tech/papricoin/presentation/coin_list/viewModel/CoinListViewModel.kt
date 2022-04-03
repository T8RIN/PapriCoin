package ru.tech.papricoin.presentation.coin_list.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.domain.use_case.get_coins.GetCoinsUseCase
import ru.tech.papricoin.domain.use_case.get_overview.GetOverviewUseCase
import ru.tech.papricoin.presentation.utils.Action
import ru.tech.papricoin.presentation.utils.UIState
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getOverviewUseCase: GetOverviewUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf<UIState<List<Coin>>>(UIState.Empty())
    val coinListState: State<UIState<List<Coin>>> = _coinListState

    private val _overviewState = mutableStateOf<UIState<Overview?>>(UIState.Empty())
    val overviewState: State<UIState<Overview?>> = _overviewState

    init {
        getOverview()
        getCoins()
    }

    private fun getOverview() {
        getOverviewUseCase().onEach { result ->
            when (result) {
                is Action.Success -> {
                    _overviewState.value = UIState.Success(result.data)
                }
                is Action.Empty -> {
                    _overviewState.value = UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    _overviewState.value = UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Action.Success -> {
                    currentList = result.data ?: emptyList()
                    _coinListState.value = UIState.Success(currentList)
                }
                is Action.Empty -> {
                    _coinListState.value = UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    _coinListState.value = UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

    var currentList: List<Coin> = listOf()
    fun filterFor(query: String) {
        _coinListState.value = UIState.Success(
            if (query.trim().isNotEmpty()) currentList.filter {
                it.name.lowercase().contains(query) or it.symbol.lowercase().contains(query)
            } else currentList
        )
    }

}