package com.ymg.architecture.di.core.local

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
class DataStoreModule {
    private val Context.userDataStore by preferencesDataStore(
        name = "user.preferences_pb"
    )

    @Singleton
    @Provides
    fun provideAppDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userDataStore
}
