package com.ymg.retrofit

sealed interface ApiCallback<out T> {
    data class Success<T>(
        val body: T
    ) : ApiCallback<T>

    sealed interface Failure : ApiCallback<Nothing> {
        // API 응답 실패
        data class HttpError(
            val code: Int,
            val message: String,
            val body: String
        ) : Failure

        // Network 에러
        data class NetworkError(
            val throwable: Throwable
        ) : Failure

        // 알 수 없는 에러
        data class UnknownError(
            val throwable: Throwable
        ) : Failure
    }
}