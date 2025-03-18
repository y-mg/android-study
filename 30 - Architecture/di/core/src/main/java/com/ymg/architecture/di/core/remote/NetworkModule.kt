package com.ymg.architecture.di.core.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ymg.architecture.data.core.remote.api.ApiCallAdapterFactory
import com.ymg.architecture.data.core.remote.interceptor.LoggingInterceptor
import com.ymg.architecture.data.core.remote.interceptor.TokenInterceptor
import com.ymg.architecture.di.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        prettyPrint = true // 읽기 쉽게 포맷팅
        ignoreUnknownKeys = true // 정의되지 않은 키 무시
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        json: Json
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(
            timeout = 10,
            unit = TimeUnit.SECONDS
        )
        .connectTimeout(
            timeout = 10,
            unit = TimeUnit.SECONDS
        )
        .writeTimeout(
            timeout = 10,
            unit = TimeUnit.SECONDS
        )
        .addInterceptor(tokenInterceptor)
        .addInterceptor(
            HttpLoggingInterceptor(LoggingInterceptor(json)).apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        )
        .build()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(ApiCallAdapterFactory())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}
