package com.ymg.effect.ui.example

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

@Composable
fun ExampleLaunchedEffect() {
    val data = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // 비동기 작업 수행
        data.value = fetchDataFromNetwork()
    }

    // UI에 데이터 표시
    Text(text = data.value ?: "Loading...")
}

// 네트워크 요청 등의 비동기 작업
private suspend fun fetchDataFromNetwork(): String {
    delay(2000)
    return "Fetched Data"
}