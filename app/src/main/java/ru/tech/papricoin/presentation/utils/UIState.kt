package ru.tech.papricoin.presentation.utils

sealed class UIState {
    class Empty(var message: String? = null) : UIState()
    object Loading : UIState()
    class Success<T>(val data: T) : UIState()
}