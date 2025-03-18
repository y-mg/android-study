package com.ymg.architecture.data.core.repository

import com.ymg.architecture.data.core.local.datasource.LocalUserDataSource
import com.ymg.architecture.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
) : UserRepository {
    override suspend fun setAccessToken(
        accessToken: String
    ) = localUserDataSource.setAccessToken(
        accessToken = accessToken
    )
}