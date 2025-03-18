package com.ymg.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataStoreRepository @Inject constructor(
    private val userPreferencesDataStore: DataStore<Preferences>
) {
    private val accessTokenKey = stringPreferencesKey("ACCESS_TOKEN")
    private val refreshTokenKey = stringPreferencesKey("REFRESH_TOKEN")

    val accessToken: Flow<String> = userPreferencesDataStore.data.map {
        it[accessTokenKey] ?: ""
    }
    val refreshToken: Flow<String> = userPreferencesDataStore.data.map {
        it[refreshTokenKey] ?: ""
    }

    suspend fun setAccessToken(
        accessToken: String
    ) = userPreferencesDataStore.edit {
        it[accessTokenKey] = accessToken
    }

    suspend fun setRefreshToken(
        refreshToken: String
    ) = userPreferencesDataStore.edit {
        it[refreshTokenKey] = refreshToken
    }
}