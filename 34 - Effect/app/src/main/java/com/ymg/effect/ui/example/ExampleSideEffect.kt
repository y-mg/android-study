package com.ymg.effect.ui.example

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ExampleSideEffect() {
    var count by remember { mutableIntStateOf(0) }

    SideEffect {
        // 외부 상태를 변경
        Log.d("SideEffect", "Count: $count")
    }

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment")
    }
}