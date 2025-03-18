package com.ymg.architecture.data.local.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ymg.architecture.data.core.local.secret.SecretHelper
import com.ymg.architecture.util.kotlin.safeEmptyLet
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val secretHelper: SecretHelper
) {
    private val accessTokenKey = stringPreferencesKey("ACCESS_TOKEN")
    private val refreshTokenKey = stringPreferencesKey("REFRESH_TOKEN")

    val accessToken: Flow<String> = dataStore.data.map {
        safeEmptyLet(it[accessTokenKey]) { (accessToken) ->
            secretHelper.decrypt(accessToken)
        } ?: ""
    }

    suspend fun setAccessToken(
        accessToken: String
    ) = dataStore.edit {
        it[accessTokenKey] = secretHelper.encrypt(accessToken)
    }
}
