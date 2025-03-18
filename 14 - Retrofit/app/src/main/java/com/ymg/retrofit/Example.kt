package com.ymg.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.IOException
import java.util.concurrent.TimeUnit

val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer YOUR_TOKEN") // 헤더 추가
            .build()
        chain.proceed(request)
    }
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 전체 응답 로그 출력
        }
    )
    .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 설정
    .readTimeout(30, TimeUnit.SECONDS) // 읽기 타임아웃 설정
    .build()

val request = Request.Builder()
    .url("https://api.example.com/users/1")
    .build()

fun exampleRequestWithOkHttp() {
    val request = Request.Builder()
        .url("https://api.example.com/users/1")
        .build()

    val call = client.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Request failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            response.body?.let { responseBody ->
                println("Response: $responseBody")
            }
        }
    })
}

val json: Json = Json {
    prettyPrint = true // 읽기 쉽게 포맷팅
    ignoreUnknownKeys = true // 정의되지 않은 키 무시
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/") // 기본 URL 설정
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient.Builder().build()) // OkHttp 클라이언트 추가
    .build()

suspend fun fetchUser() {
    val userApiService = retrofit.create(UserApiService::class.java)
    val response = userApiService.getUser()
    if (response.isSuccessful) {
        val user = response.body()
        println("Response: $user")
    } else {
        println("Error: ${response.errorBody()?.string()}")
    }
}

val retrofitWithCallAdapter: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/") // 기본 URL 설정
    .addCallAdapterFactory(ApiCallAdapterFactory())
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient.Builder().build()) // OkHttp 클라이언트 추가
    .build()