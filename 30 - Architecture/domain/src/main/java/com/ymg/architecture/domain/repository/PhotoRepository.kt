package com.ymg.architecture.domain.repository

import androidx.paging.PagingData
import com.ymg.architecture.domain.model.PhotoModel
import com.ymg.architecture.domain.remote.api.ApiResult
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<PagingData<PhotoModel>>

    suspend fun getPhoto(id: String): ApiResult<PhotoModel>
}