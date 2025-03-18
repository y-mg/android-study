package com.ymg.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserProtoDataStoreRepository @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>
) {
    val userName: Flow<String> = userProtoDataStore.data.map {
        it.userName ?: ""
    }
    val userEmail: Flow<String> = userProtoDataStore.data.map {
        it.userEmail ?: ""
    }

    suspend fun setUserName(
        userName: String
    ) = userProtoDataStore.updateData {
        it.toBuilder().setUserName(userName).build()
    }

    suspend fun setEmail(
        userEmail: String
    ) = userProtoDataStore.updateData {
        it.toBuilder().setUserEmail(userEmail).build()
    }
}