package com.ymg.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("api/v1/user")
    suspend fun getUser(): Response<UserResponse>
}