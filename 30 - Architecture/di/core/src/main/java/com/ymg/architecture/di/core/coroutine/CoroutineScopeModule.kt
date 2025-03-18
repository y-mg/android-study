package com.ymg.architecture.di.core.coroutine

import com.ymg.architecture.di.qualifier.DefaultCoroutineScope
import com.ymg.architecture.di.qualifier.IoCoroutineScope
import com.ymg.architecture.di.qualifier.MainCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

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
