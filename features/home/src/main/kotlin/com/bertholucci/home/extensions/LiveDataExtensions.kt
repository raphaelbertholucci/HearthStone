package com.bertholucci.home.extensions

import androidx.lifecycle.MutableLiveData
import com.bertholucci.domain.helper.HearthStoneResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <V> MutableLiveData<HearthStoneResponse<V>>.showLoading() {
    value = HearthStoneResponse.Loading(true)
}

fun <V> MutableLiveData<HearthStoneResponse<V>>.hideLoading() {
    value = HearthStoneResponse.Loading(false)
}

fun <V> MutableLiveData<HearthStoneResponse<V>>.success(response: V) {
    value = HearthStoneResponse.Success(response)
}

fun <V> MutableLiveData<HearthStoneResponse<V>>.failure(error: Throwable) {
    value = HearthStoneResponse.Failure(error)
}

fun <T : Any> Flow<T>.response(
    liveData: MutableLiveData<HearthStoneResponse<T>>,
    viewModelScope: CoroutineScope
) {
    this.onStart { liveData.showLoading() }
        .onCompletion { liveData.hideLoading() }
        .map { liveData.success(it) }
        .catch { liveData.failure(it) }
        .launchIn(viewModelScope)
}
