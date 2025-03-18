package com.ymg.retrofit

import java.io.IOException
import java.lang.reflect.Type
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall<T>(
    private val delegate: Call<T>,
    private val successType: Type
) : Call<ApiCallback<T>> {
    override fun enqueue(
        callback: Callback<ApiCallback<T>>
    ) = delegate.enqueue(
        object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                callback.onResponse(this@ApiCall, Response.success(response.toApiCallback()))
            }

            override fun onFailure(
                call: Call<T?>,
                throwable: Throwable
            ) {
                val error = if (throwable is IOException) {
                    ApiCallback.Failure.NetworkError(throwable)
                } else {
                    ApiCallback.Failure.UnknownError(throwable)
                }
                callback.onResponse(this@ApiCall, Response.success(error))
            }

            private fun Response<T>.toApiCallback(): ApiCallback<T> {
                val body = body()
                val errorBody = errorBody()
                val code = code()
                val message = message()

                return if (isSuccessful) {
                    body?.let {
                        ApiCallback.Success(it)
                    } ?: run {
                        ApiCallback.Failure.UnknownError(IllegalStateException("Body is null."))
                    }
                } else {
                    errorBody?.let {
                        ApiCallback.Failure.HttpError(
                            code = code,
                            message = message,
                            body = it.string()
                        )
                    } ?: run {
                        ApiCallback.Failure.UnknownError(IllegalStateException("ErrorBody is null."))
                    }
                }
            }
        }
    )

    override fun timeout(): Timeout = delegate.timeout()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<ApiCallback<T>> = ApiCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiCallback<T>> = throw UnsupportedOperationException("This adapter does not support sync execution")

    override fun request(): Request = delegate.request()
}