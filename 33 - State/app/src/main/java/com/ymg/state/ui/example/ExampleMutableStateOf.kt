package com.ymg.state.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleMutableStateOf() {
    // mutableStateOf 를 사용하여 상태를 생성한다.
    // 버튼 클릭 시 카운트 값이 증가하거나 감소한다.
    var count by remember { mutableIntStateOf(0) } // 초기 값 0

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 현재 카운트 값을 표시
        Text(
            text = "Count: $count"
        )

        // 버튼을 눌러 카운트를 증가시키는 함수
        Button(
            onClick = {
                count++
            },
            modifier = Modifier.padding(
                top = 16.dp
            )
        ) {
            Text(text = "Increase Count")
        }

        // 버튼을 눌러 카운트를 감소시키는 함수
        Button(
            onClick = {
                count--
            },
            modifier = Modifier.padding(
                top = 8.dp
            )
        ) {
            Text(text = "Decrease Count")
        }
    }
}