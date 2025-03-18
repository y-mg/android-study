package com.ymg.architecture.domain.repository

interface UserRepository {
    suspend fun setAccessToken(
        accessToken: String
    )
}