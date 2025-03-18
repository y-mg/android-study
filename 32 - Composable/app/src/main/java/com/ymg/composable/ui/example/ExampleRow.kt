package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExampleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // 정렬 방식을 지정
        verticalAlignment = Alignment.CenterVertically // 자식 요소의 세로 정렬을 조정
    ) {
        Text(
            text = "Start",
            color = Color.Black
        )
        Text(
            text = "Center",
            color = Color.Blue
        )
        Text(
            text = "End",
            color = Color.Red
        )
    }
}