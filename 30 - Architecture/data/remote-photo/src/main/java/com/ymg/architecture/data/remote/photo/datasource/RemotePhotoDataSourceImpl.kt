package com.ymg.architecture.data.remote.photo.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ymg.architecture.data.core.remote.api.ApiCallback
import com.ymg.architecture.data.core.remote.datasource.RemotePhotoDataSource
import com.ymg.architecture.data.core.remote.model.response.PhotoResponse
import com.ymg.architecture.data.remote.photo.paging.RemotePhotoPagingSource
import com.ymg.architecture.data.remote.photo.api.PhotoApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemotePhotoDataSourceImpl @Inject constructor(
    private val api: PhotoApi
): RemotePhotoDataSource {

    override fun getPhotos(): Flow<PagingData<PhotoResponse>> = Pager(
        PagingConfig(
            initialLoadSize = 20,
            pageSize = 20,
            prefetchDistance = 2
        )
    ) {
        RemotePhotoPagingSource(api)
    }.flow

    override suspend fun getPhoto(
        id: String
    ): ApiCallback<PhotoResponse> = api.getPhoto(id)
}
