package com.ymg.architecture.data.remote.photo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ymg.architecture.data.core.remote.api.ApiCallback
import com.ymg.architecture.data.core.remote.api.toApiException
import com.ymg.architecture.data.core.remote.model.response.PhotoResponse
import com.ymg.architecture.data.remote.photo.api.PhotoApi

class RemotePhotoPagingSource (
    private val api: PhotoApi,
) : PagingSource<Int, PhotoResponse>() {
    companion object {
        private const val START_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        val page = params.key ?: START_PAGE_INDEX

        return try {
            val data = api.getPhotos(
                mutableMapOf<String, Any?>().apply {
                    this["page"] = page
                    this["per_page"] = 20
                }
            )

            when (data) {
                is ApiCallback.Success -> {
                    data.body
                    LoadResult.Page(
                        data = data.body,
                        prevKey = if (page == START_PAGE_INDEX) {
                            null
                        } else {
                            page.minus(1)
                        },
                        nextKey = if (data.body.isNotEmpty()) {
                            page.plus(1)
                        } else {
                            null
                        }
                    )
                }

                is ApiCallback.Failure.HttpError -> {
                    LoadResult.Error(
                        ApiCallback.Failure.HttpError(
                            data.code,
                            data.message,
                            data.body
                        ).toApiException()
                    )
                }

                is ApiCallback.Failure.NetworkError -> {
                    LoadResult.Error(
                        ApiCallback.Failure.NetworkError(data.throwable).toApiException()
                    )
                }

                is ApiCallback.Failure.UnknownError -> {
                    LoadResult.Error(
                        ApiCallback.Failure.UnknownError(data.throwable).toApiException()
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(ApiCallback.Failure.UnknownError(e).toApiException())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}