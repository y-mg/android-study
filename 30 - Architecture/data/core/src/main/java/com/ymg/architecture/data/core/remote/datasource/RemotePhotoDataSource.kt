package com.ymg.architecture.data.core.remote.datasource

import androidx.paging.PagingData
import com.ymg.architecture.data.core.remote.api.ApiCallback
import com.ymg.architecture.data.core.remote.model.response.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface RemotePhotoDataSource {
    fun getPhotos(): Flow<PagingData<PhotoResponse>>

    suspend fun getPhoto(
        id: String
    ): ApiCallback<PhotoResponse>
}
