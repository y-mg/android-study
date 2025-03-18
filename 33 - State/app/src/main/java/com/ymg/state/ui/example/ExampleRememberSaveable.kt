package com.ymg.state.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleRememberSaveable() {
    // rememberSaveable 을 사용하여 상태를 저장한다.
    // 화면 회전 시에도 상태가 유지된다.
    var text by rememberSaveable { mutableStateOf("Hello, World!") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 상태값을 표시하는 텍스트
        Text(
            text = "Current Text: $text"
        )

        // 텍스트를 변경하는 입력 필드
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = {
                Text("Enter text")
            }
        )

        // 버튼 클릭 시 텍스트 초기화
        Button(
            onClick = {
                text = ""
            },
            modifier = Modifier.padding(
                top = 16.dp
            )
        ) {
            Text(text = "Clear Text")
        }
    }
}