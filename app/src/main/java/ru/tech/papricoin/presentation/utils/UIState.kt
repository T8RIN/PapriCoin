package ru.tech.papricoin.presentation.utils

sealed class UIState<T> {
    class Empty<T>(var message: String? = null) : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
}