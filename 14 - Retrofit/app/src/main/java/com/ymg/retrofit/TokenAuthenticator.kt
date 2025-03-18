package com.ymg.retrofit

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newAccessToken: String? = try {
            runBlocking {
                withTimeout(10_000) {
                    tokenManager.refreshTokenSafely()
                }
            }
        } catch (_: TimeoutCancellationException) {
            null
        }

        if (newAccessToken == null) {
            // RefreshToken 도 만료된 경우, 로그인 페이지로 리디렉션
            return null
        }

        // 새로운 AccessToken 으로 헤더를 업데이트하여 재시도
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }
}