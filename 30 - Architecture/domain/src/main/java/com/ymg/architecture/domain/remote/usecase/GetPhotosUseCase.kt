package com.ymg.architecture.domain.remote.usecase

import androidx.paging.PagingData
import com.ymg.architecture.domain.model.PhotoModel
import com.ymg.architecture.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(): Flow<PagingData<PhotoModel>> = repository.getPhotos()
}