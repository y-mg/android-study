package com.ymg.architecture.di.core.helper

import com.ymg.architecture.data.core.local.secret.SecretHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HelperModule {
    @Singleton
    @Provides
    fun provideSecretHelper(): SecretHelper = SecretHelper()
}
