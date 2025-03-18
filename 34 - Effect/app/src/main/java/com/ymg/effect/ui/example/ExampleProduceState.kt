package com.ymg.effect.ui.example

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun ExampleProduceState() {
    val result = produceState<String?>(initialValue = null) {
        value = fetchDataFromNetwork()
    }

    Text(text = result.value ?: "Loading...")
}

// 네트워크 요청 등의 비동기 작업
private suspend fun fetchDataFromNetwork(): String {
    delay(2000)
    return "Fetched Data"
}