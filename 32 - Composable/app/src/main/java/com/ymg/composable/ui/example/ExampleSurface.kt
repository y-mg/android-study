package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExampleSurface() {
    Surface(
        color = Color.LightGray, // Surface 의 배경색
        shape = RoundedCornerShape(8.dp), // 모서리를 둥글게 처리
        shadowElevation = 4.dp, // 그림자 효과 추가
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Surface Example",
            modifier = Modifier.padding(16.dp)
        )
    }
}