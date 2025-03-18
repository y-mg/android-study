package com.ymg.effect.ui.example

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun ExampleSnapshotFlow() {
    var count by remember { mutableIntStateOf(0) }
    val flow = snapshotFlow { count }

    LaunchedEffect(flow) {
        flow.collect { newCount ->
            // 상태 변경을 처리
            println("Count changed: $newCount")
        }
    }

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment")
    }
}