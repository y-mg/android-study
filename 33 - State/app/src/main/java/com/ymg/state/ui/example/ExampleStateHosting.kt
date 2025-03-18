package com.ymg.state.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun ExampleStateHosting() {
    // 상태 정의: 평과 제곱미터 값을 저장하는 상태 변수
    var pyeong by rememberSaveable { mutableStateOf("23") }
    var squareMeter by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }

    // 상태를 Stateless 컴포저블에 전달
    ExampleStateless(
        pyeong = pyeong,
        squareMeter = squareMeter,
        onPyeongChange = { newPyeong ->
            // 사용자가 입력한 값이 비어 있거나 숫자가 아닌 경우 처리
            if (newPyeong.isBlank()) {
                pyeong = ""
                squareMeter = ""
                return@ExampleStateless
            }
            val numericValue = newPyeong.toFloatOrNull() ?: return@ExampleStateless
            pyeong = newPyeong
            squareMeter = (numericValue * 3.306).toString() // 평을 제곱미터로 변환
        }
    )
}

@Composable
fun ExampleStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // 평을 입력받는 OutlinedTextField
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange, // 부모 컴포저블에서 전달된 콜백을 통해 상태 변경
            label = { Text("평") }
        )
        // 제곱미터는 읽기 전용으로 표시
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {}, // 상태를 변경하지 않음
            label = { Text("제곱미터") },
            readOnly = true // 제곱미터는 읽기 전용
        )
    }
}