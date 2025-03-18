package com.ymg.retrofit

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TokenManager(
    private val authApiService: AuthApiService
) {
    private val mutex = Mutex()

    // 갱신된 토큰을 안전하게 반환
    suspend fun refreshTokenSafely(): String? {
        return if (mutex.isLocked) {
            // 이미 갱신 중이면 기존 토큰을 반환
            mutex.withLock { getAccessToken() }
        } else {
            // 토큰 갱신 중에 다른 요청이 오지 않도록 동기화
            mutex.withLock {
                refreshToken() // 실제 토큰 갱신 수행
            }
        }
    }

    // 실제로 토큰을 갱신하는 메서드
    private suspend fun refreshToken(): String? {
        // 저장된 refresh token 을 가져와 새로운 access token 을 갱신
        val refreshToken = getRefreshToken()
        val newAccessToken = authApiService.getNewAccessToken(refreshToken)
        return newAccessToken.body()?.newAccessToken
    }

    // 토큰을 얻는 메서드
    private suspend fun getAccessToken(): String {
        // 이미 갱신된 토큰 반환
        return "access_token"
    }

    // RefreshToken 을 반환하는 메서드
    private suspend fun getRefreshToken(): String {
        // 실제로 저장된 Refresh Token 을 반환하는 로직
        return "refresh_token"
    }
}