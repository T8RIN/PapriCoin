package ru.tech.papricoin.presentation.favorite_coins.viewModel

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.use_case.favorite_coins.get_favorite_coins.GetFavoriteCoinsUseCase
import ru.tech.papricoin.presentation.ui.utils.Action
import ru.tech.papricoin.presentation.ui.utils.UIState
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase
) : ViewModel() {

    @ExperimentalMaterial3Api
    val scrollBehavior = mutableStateOf(TopAppBarDefaults.pinnedScrollBehavior())

    private val _coinListState = mutableStateOf<UIState<List<Coin>>>(UIState.Empty())
    val coinListState: State<UIState<List<Coin>>> = _coinListState


    init {
        reload()
    }

    fun reload() {
        getFavoriteCoins()
    }

    private fun getFavoriteCoins() {
        getFavoriteCoinsUseCase().onEach { result ->
            when (result) {
                is Action.Success -> {
                    _coinListState.value = UIState.Success(result.data ?: emptyList())
                }
                is Action.Empty -> {
                    _coinListState.value = UIState.Empty(result.message ?: "Unexpected error")
                }
                is Action.Loading -> {
                    _coinListState.value = UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

}