package com.ymg.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

class FlowViewModel : ViewModel() {
    val flow = flow {
        for (i in 1..5) {
            emit(i) // 데이터 방출
            delay(1000) // 1초마다 방출
        }
    }

    val stateflow: StateFlow<Int> = flow.stateIn(
        scope = viewModelScope, // scope 는 StateFlow 가 Flow 로부터 데이터를 구독할 CoroutineScope 를 지정
        started = SharingStarted.WhileSubscribed(), // started 는 언제부터 Flow 를 구독할지 설정하는 옵션
        initialValue = 0 // 초기값을 설정하는 옵션
    )

    val sharedFow: SharedFlow<Int> = flow.shareIn(
        scope = viewModelScope, // scope 는 StateFlow 가 Flow 로부터 데이터를 구독할 CoroutineScope 를 지정
        started = SharingStarted.WhileSubscribed(), // started 는 언제부터 Flow 를 구독할지 설정하는 옵션
        replay = 0 // 이전 데이터를 몇 개까지 재전송할지를 설정하는 옵션
    )
}