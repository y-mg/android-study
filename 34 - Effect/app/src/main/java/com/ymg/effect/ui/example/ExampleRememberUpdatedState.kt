package com.ymg.effect.ui.example

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue

@Composable
fun ExampleRememberUpdatedState() {
    var count by remember { mutableIntStateOf(0) }
    val updatedCount = rememberUpdatedState(count)

    // LaunchedEffect 는 코루틴이 시작된 후 상태가 변경되더라도 코루틴 내부에서는 초기 상태만 참조한다.
    // 이때 rememberUpdatedState 를 사용하면 항상 최신 상태를 참조할 수 있어 예기치 않은 버그를 방지할 수 있다.
    LaunchedEffect(updatedCount.value) {
        // 최신 값 참조
        println("Count: ${updatedCount.value}")
    }

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment Count")
    }
}