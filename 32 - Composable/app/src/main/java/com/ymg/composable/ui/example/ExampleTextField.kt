package com.ymg.composable.ui.example

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ExampleTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text, // 입력된 텍스트 값
        onValueChange = { // 텍스트 변경 시 호출되는 콜백 함수
            text = it
        },
        label = { // 입력 필드 위에 표시할 라벨
            Text("Enter text")
        }
    )
}