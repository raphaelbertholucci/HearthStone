package com.bertholucci.domain.helper

sealed class HearthStoneResponse<out V> {
    data class Success<out V>(val value: V) : HearthStoneResponse<V>()
    data class Failure(val error: Throwable) : HearthStoneResponse<Nothing>()
    data class Loading(val loading: Boolean) : HearthStoneResponse<Nothing>()

    private fun <V> success(value: V): HearthStoneResponse<V> = Success(value)
    private fun failure(value: Throwable): HearthStoneResponse<Nothing> = Failure(value)
    private fun loading(value: Boolean): HearthStoneResponse<Nothing> = Loading(value)

    fun <V> response(action: () -> V): HearthStoneResponse<V> =
        try {
            success(action())
        } catch (e: Exception) {
            failure(e)
        }
}

inline fun <V, A> HearthStoneResponse<V>.fold(
    error: (Throwable) -> A,
    loading: (Boolean) -> A,
    success: (V) -> A
): A = when (this) {
    is HearthStoneResponse.Failure -> error(this.error)
    is HearthStoneResponse.Success -> success(this.value)
    is HearthStoneResponse.Loading -> loading(this.loading)
}