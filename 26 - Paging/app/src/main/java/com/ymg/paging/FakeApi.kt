package com.ymg.paging

import kotlinx.coroutines.delay

class FakeApi {
    suspend fun fetchData(page: Int): List<DataEntity> {
        delay(1000) // 네트워크 딜레이 시뮬레이션
        return List(20) { index ->
            DataEntity(
                id = (page - 1) * 20 + index,
                name = "Server Item ${(page - 1) * 20 + index}"
            )
        }
    }
}