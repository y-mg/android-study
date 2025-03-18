package com.ymg.retrofit

import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter

class ApiCallAdapter<T>(
    private val successType: Type
) : CallAdapter<T, Call<ApiCallback<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(
        call: Call<T>
    ): Call<ApiCallback<T>> = ApiCall(call, successType)
}