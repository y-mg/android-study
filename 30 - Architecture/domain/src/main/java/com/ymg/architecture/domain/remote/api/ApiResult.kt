package com.ymg.architecture.domain.remote.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

sealed class ApiResult<out T> {
    data class Success<T>(
        val data: T
    ) : ApiResult<T>()

    data class Failure<T>(
        val throwable: Throwable
    ) : ApiResult<T>()
}

inline fun <T : Any> Flow<ApiResult<T>>.onSuccess(
    crossinline action: suspend (data: T) -> Unit
): Flow<ApiResult<T>> = onEach {
    if (it is ApiResult.Success) {
        action(it.data)
    }
}

inline fun <T : Any> Flow<ApiResult<T>>.onFailure(
    crossinline action: suspend (throwable: Throwable) -> Unit
): Flow<ApiResult<T>> = onEach {
    if (it is ApiResult.Failure) {
        action(it.throwable)
    }
}
