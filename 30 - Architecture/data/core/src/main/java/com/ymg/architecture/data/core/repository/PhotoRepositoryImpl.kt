package com.ymg.architecture.data.core.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.ymg.architecture.domain.model.PhotoModel
import com.ymg.architecture.data.core.remote.api.callback
import com.ymg.architecture.data.core.remote.datasource.RemotePhotoDataSource
import com.ymg.architecture.data.core.remote.model.response.PhotoResponse.Companion.toModel
import com.ymg.architecture.domain.remote.api.ApiResult
import com.ymg.architecture.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemotePhotoDataSource
): PhotoRepository {
    override fun getPhotos(): Flow<PagingData<PhotoModel>> {
        val data = remoteDataSource.getPhotos().map {
            it.map { response ->
                response.toModel()
            }
        }

        return data
    }

    override suspend fun getPhoto(
        id: String
    ): ApiResult<PhotoModel> = remoteDataSource.getPhoto(id).callback {
        it.toModel()
    }
}