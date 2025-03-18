package com.ymg.composable.ui.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExampleModifier() {
    Text(
        text = "Hello, Modifier!",
        modifier = Modifier
            .padding(16.dp) // 패딩 추가
            .background(Color.Gray) // 배경색 추가
            .clickable {
                println("Text Clicked")
            } // 클릭 이벤트 추가
    )
}