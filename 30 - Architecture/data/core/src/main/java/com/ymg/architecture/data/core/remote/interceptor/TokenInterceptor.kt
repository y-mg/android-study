package com.ymg.architecture.data.core.remote.interceptor

import com.ymg.architecture.data.core.local.datasource.LocalUserDataSource
import javax.inject.Inject
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor @Inject constructor(
    private val dataSource: LocalUserDataSource
) : Interceptor {
    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val accessToken: String = try {
            runBlocking {
                withTimeout(10_000) {
                    dataSource.getAccessToken().first()
                }
            }
        } catch (_: TimeoutCancellationException) {
            ""
        }

        val request = if (accessToken.isNotEmpty()) {
            chain.request()
                .newBuilder()
                .addHeader(AUTHORIZATION, accessToken)
                .build()
        } else {
            chain.request()
        }

        val response = chain.proceed(request)
        return response
    }
}
