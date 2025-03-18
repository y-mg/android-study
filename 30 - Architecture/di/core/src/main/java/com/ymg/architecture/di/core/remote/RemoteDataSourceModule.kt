package com.ymg.architecture.di.core.remote

import com.ymg.architecture.data.core.remote.datasource.RemotePhotoDataSource
import com.ymg.architecture.data.remote.photo.datasource.RemotePhotoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemotePhotoDataSource(
        dataSource: RemotePhotoDataSourceImpl
    ): RemotePhotoDataSource
}