package com.ymg.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PagingSourceViewModel : ViewModel() {
    val pagingDataFlow = Pager(
        config = PagingConfig(
            // 최초 로드할 데이터 크기(기본값: pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER)
            initialLoadSize = 20,
            // 각 페이지의 데이터 크기
            pageSize = 20,
            // 다음 데이터를 로드할 임계치 거리
            prefetchDistance = 5,
            // 플레이스 홀더를 활성화할지 여부(true 설정 시 로드되지 않은 항목을 null 로 채움)
            enablePlaceholders = false
        )
    ) {
        FakePagingSource()
    }.flow.cachedIn(viewModelScope)
}