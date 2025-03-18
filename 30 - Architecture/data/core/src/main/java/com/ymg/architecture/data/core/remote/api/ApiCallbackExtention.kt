package com.ymg.architecture.data.core.remote.api

import com.ymg.architecture.data.core.remote.model.response.ErrorResponse
import com.ymg.architecture.domain.remote.api.ApiResult
import com.ymg.architecture.domain.remote.api.exception.ApiException
import com.ymg.architecture.domain.remote.api.exception.CommonException
import kotlinx.serialization.json.Json

fun ApiCallback.Failure.toApiException(): Exception = when (this) {
    is ApiCallback.Failure.HttpError -> {
        val json = Json {
            ignoreUnknownKeys = true // 정의되지 않은 키 무시
        }
        val errorBody = json.decodeFromString<ErrorResponse>(body)
        val exception = ApiException(
            message = errorBody.message,
            status = errorBody.status,
            code = errorBody.code
        )

        when (code) {
            in 500..599 -> {
                CommonException.ServerException()
            }
            else -> {
                exception
            }
        }
    }
    is ApiCallback.Failure.NetworkError -> {
        CommonException.NetworkException()
    }
    is ApiCallback.Failure.UnknownError -> {
        CommonException.UnknownException()
    }
}

fun <T, R> ApiCallback<T>.callback(
    mapper: (T) -> R
): ApiResult<R> = if (this is ApiCallback.Success) {
    ApiResult.Success(mapper(body))
} else {
    ApiResult.Failure((this as ApiCallback.Failure).toApiException())
}
