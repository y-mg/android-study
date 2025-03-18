package com.ymg.datastore

import android.content.Context
import androidx.datastore.core.DataStore
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
object PreferencesDataStoreModule {
    private val Context.userPreferencesDataStore by preferencesDataStore(
        name = "user.preferences_pb"
    )

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userPreferencesDataStore

    @Singleton
    @Provides
    fun provideUserPreferencesDataStoreRepository(
        userPreferencesDataStore: DataStore<Preferences>
    ): UserPreferencesDataStoreRepository = UserPreferencesDataStoreRepository(
        userPreferencesDataStore = userPreferencesDataStore
    )
}