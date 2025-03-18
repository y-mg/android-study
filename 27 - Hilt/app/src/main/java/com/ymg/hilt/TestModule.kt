package com.ymg.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestModule {
    @Singleton
    @Provides
    fun provideTest(
        // Context 중에서 Application 범위의 Context 를 명확히 지정하여 Hilt 를 통해 주입받을 수 있음
        @ApplicationContext context: Context
    ): TestClass = TestClass()
}