package com.ymg.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class FakePagingSource : PagingSource<Int, Data>() {
    // 데이터를 가져오는 로직을 구현하는 함수
    // LoadResult.Page 를 반환해 이전 및 다음 페이지를 정의
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        val data = List(20) { index ->
            Data(
                id = (page - 1) * 20 + index, 
                name = "Item ${(page - 1) * 20 + index}"
            )
        }
        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page < 5) page + 1 else null
        )
    }

    // 데이터 업데이트 시 사용하며, 현재 페이지 목록을 대체할 새 데이터를 가져올 때 호출
    // anchorPosition 을 기준으로 가까운 페이지 정보를 반환해 적절한 Key 로 새 데이터를 로드
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}