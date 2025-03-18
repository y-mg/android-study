package com.ymg.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SharedFlowViewModel : ViewModel() {
    private val _sharedFlow = MutableSharedFlow<String>(
        // 이전 데이터의 개수를 설정하여 새로 구독한 구독자가 받을 수 있는 직전 데이터를 지정한다.
        // 예를 들어 replay = 1 이라면, 구독자가 가장 최근 데이터를 받을 수 있다.
        replay = 0,
        // 버퍼의 추가 크기를 설정하여, 설정된 replay 외에 버퍼에 추가로 저장할 수 있는 데이터의 양을 지정한다.
        extraBufferCapacity = 0,
        // 버퍼가 가득 찼을 때의 동작을 정의한다.
        // BufferOverflow.DROP_OLDEST -> 가장 오래된 데이터를 버리고 새로운 데이터를 수용한다.
        // BufferOverflow.DROP_LATEST -> 최신 데이터를 버리고 새 데이터를 수용하지 않는다.
        // BufferOverflow.SUSPEND -> 버퍼가 가득 찼을 때 더 이상 데이터를 받지 않고 대기한다.
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val sharedFlow: SharedFlow<String> = _sharedFlow

    init {
        sendEvent("Hello SharedFlow")
    }

    private fun sendEvent(
        message: String
    ) = viewModelScope.launch {
        _sharedFlow.emit(message)
    }
}