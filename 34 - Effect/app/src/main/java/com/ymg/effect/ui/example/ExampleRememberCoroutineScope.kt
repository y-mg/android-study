package com.ymg.effect.ui.example

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExampleRememberCoroutineScope() {
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                // 코루틴 내부에서 비동기 작업
                delay(1000)
                println("Task completed!")
            }
        }
    ) {
        Text("Start Task")
    }
}