package com.ymg.architecture.di.core.repository

import com.ymg.architecture.data.core.repository.PhotoRepositoryImpl
import com.ymg.architecture.data.core.repository.UserRepositoryImpl
import com.ymg.architecture.domain.repository.PhotoRepository
import com.ymg.architecture.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    abstract fun bindPhotoRepository(
        repository: PhotoRepositoryImpl
    ): PhotoRepository
}
