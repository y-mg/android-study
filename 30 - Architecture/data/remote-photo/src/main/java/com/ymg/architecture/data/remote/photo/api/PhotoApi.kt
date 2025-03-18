package com.ymg.architecture.data.remote.photo.api

import com.ymg.architecture.data.core.remote.api.ApiCallback
import com.ymg.architecture.data.core.remote.model.response.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface PhotoApi {
    @GET("photos")
    suspend fun getPhotos(
        @QueryMap
        params: MutableMap<String, Any?>
    ): ApiCallback<List<PhotoResponse>>


    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id")
        id: String
    ): ApiCallback<PhotoResponse>
}
