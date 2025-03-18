package com.ymg.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtoDataStoreModule {
    private val Context.userProtoDataStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserProtoSerializer
    )

    @Provides
    @Singleton
    fun provideUserProtoDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.userProtoDataStore
    }

    @Provides
    @Singleton
    fun provideUserProtoDataStoreRepository(
        userProtoDataStore: DataStore<UserPreferences>
    ): UserProtoDataStoreRepository = UserProtoDataStoreRepository(
        userProtoDataStore = userProtoDataStore
    )
}