package com.ymg.architecture.di.core.remote

import com.ymg.architecture.data.remote.photo.api.PhotoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun createPhotoApi(
        retrofit: Retrofit
    ): PhotoApi = retrofit.create(PhotoApi::class.java)
}
