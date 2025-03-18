package com.ymg.architecture.domain.remote.usecase

import com.ymg.architecture.domain.model.PhotoModel
import com.ymg.architecture.domain.remote.api.ApiResult
import com.ymg.architecture.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(
        id: String
    ): Flow<ApiResult<PhotoModel>> = flow {
        emit(repository.getPhoto(id))
    }
}