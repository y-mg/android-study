package com.ymg.architecture.data.local.user.datasource

import com.ymg.architecture.data.core.local.datasource.LocalUserDataSource
import com.ymg.architecture.data.local.user.UserDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : LocalUserDataSource {
    override fun getAccessToken(): Flow<String> = userDataStore.accessToken

    override suspend fun setAccessToken(
        accessToken: String
    ) {
        userDataStore.setAccessToken(
            accessToken = accessToken
        )
    }
}
