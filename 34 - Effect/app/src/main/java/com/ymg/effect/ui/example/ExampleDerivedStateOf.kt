package com.ymg.effect.ui.example

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// count 가 변경되지 않는다면 count * 2 를 다시 계산하지 않는다.
// count 가 변경될 때만 count * 2 를 새롭게 계산한다.
@Composable
fun ExampleDerivedStateOf() {
    // 원본 상태
    var count by remember { mutableIntStateOf(0) }

    // 파생 상태(derivedStateOf 사용): count * 2
    val derivedValue = remember {
        derivedStateOf { count * 2 }
    }

    // UI 표시
    Text("Derived Value: ${derivedValue.value}")

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment")
    }
}