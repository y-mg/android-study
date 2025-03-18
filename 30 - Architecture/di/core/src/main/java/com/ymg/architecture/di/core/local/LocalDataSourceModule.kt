package com.ymg.architecture.di.core.local

import com.ymg.architecture.data.core.local.datasource.LocalUserDataSource
import com.ymg.architecture.data.local.user.datasource.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindLocalUserDataSource(
        dataSource: LocalUserDataSourceImpl
    ): LocalUserDataSource
}
