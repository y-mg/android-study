package com.ymg.architecture.data.core.remote.api

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

class ApiCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "returnType must be parameterized as ApiCallback<Foo> or ApiCallback<out Foo>"
        }

        val wrapperType = getParameterUpperBound(0, returnType)
        if (ApiCallback::class.java != getRawType(wrapperType)) {
            return null
        }
        check(wrapperType is ParameterizedType) {
            "wrapperType must be parameterized as ApiCallback<ResponseBody>"
        }

        val bodyType = getParameterUpperBound(0, wrapperType)
        return com.ymg.architecture.data.core.remote.api.ApiCallAdapter<Any>(bodyType)
    }
}
