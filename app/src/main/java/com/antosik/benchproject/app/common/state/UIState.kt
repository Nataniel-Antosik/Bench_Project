package com.antosik.benchproject.app.common.state

sealed class UIState {
    object Loading : UIState()
    object Empty : UIState()
    data class Success<T>(val data: T) : UIState()

    fun isLoading() =
        this is Loading

    fun isEmpty() =
        this is Empty

    fun isRefreshed() =
        this is Empty || this is Success<*>

    fun isSuccess() =
        this is Success<*>

    @Suppress("UNCHECKED_CAST")
    fun <T> data() =
        (this as? Success<*>)?.data as T?
}
