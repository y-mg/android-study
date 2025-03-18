package com.ymg.architecture.data.core.local.datasource

import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {
    fun getAccessToken(): Flow<String>

    suspend fun setAccessToken(
        accessToken: String
    )
}
