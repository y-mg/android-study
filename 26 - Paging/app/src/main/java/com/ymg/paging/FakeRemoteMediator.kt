package com.ymg.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction

@OptIn(ExperimentalPagingApi::class)
class FakeRemoteMediator(
    private val api: FakeApi,
    private val database: AppDatabase
) : RemoteMediator<Int, DataEntity>() {
    private var currentPage = 1  // 페이지 번호를 직접 관리

    // 서버에서 데이터를 가져와 로컬 DB 에 저장하는 역할
    // MediatorResult.Success(endOfPaginationReached = true/false) 를 반환하여 로드 종료 여부 결정
    override suspend fun load(loadType: LoadType, state: PagingState<Int, DataEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    // Refresh 시에 네트워크에서 데이터를 가져오고 로컬 DB에 저장
                    currentPage = 1 // 페이지 번호 초기화
                    1 // 첫 페이지를 요청
                }
                LoadType.PREPEND -> {
                    // Prepend 는 처리하지 않음, 데이터 끝으로 처리
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    // 마지막 아이템을 기준으로 다음 페이지 계산
                    currentPage++  // 페이지 번호 증가
                    currentPage
                }
            }

            // API 호출
            val response = api.fetchData(page)

            // 트랜잭션 처리
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    // 새로 고침 시 기존 데이터를 삭제
                    database.dataDao().clearAll()
                }
                // 새 데이터를 DB에 삽입
                database.dataDao().insertAll(response)
            }

            // 데이터가 비어 있거나 페이지 번호가 5 이상이면 마지막 페이지로 간주
            MediatorResult.Success(endOfPaginationReached = (response.isEmpty() || page >= 5))
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    // 초기화 시점을 제어 (캐시 유지 여부 결정)
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}