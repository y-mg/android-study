package com.ymg.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateFlowViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(0) // 초기값 설정 필수
    val stateFlow: StateFlow<Int> = _stateFlow

    init {
        increment()
    }

    private fun increment() {
        _stateFlow.value = 100 // 최신 상태 갱신
    }
}