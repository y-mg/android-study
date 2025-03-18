package com.ymg.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineScopeModule {
    @DefaultCoroutineScope
    @Singleton
    @Provides
    fun provideDefaultCoroutineScope(): CoroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Default
    )

    @MainCoroutineScope
    @Singleton
    @Provides
    fun provideMainCoroutineScope(): CoroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Main
    )

    @IoCoroutineScope
    @Singleton
    @Provides
    fun provideIoCoroutineScope(): CoroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.IO
    )
}