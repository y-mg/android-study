package com.ymg.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {
    @POST("api/v1/auth/token/{refreshToken}")
    suspend fun getNewAccessToken(
        @Path("refreshToken")
        refreshToken: String
    ): Response<TokenResponse>
}