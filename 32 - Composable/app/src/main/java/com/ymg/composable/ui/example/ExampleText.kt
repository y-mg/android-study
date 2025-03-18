package com.ymg.composable.ui.example

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ExampleText() {
    Text(
        text = "Hello, Compose!",
        fontSize = 20.sp, // 글자 크기
        fontWeight = FontWeight.Bold, // 글자 굵기
        color = Color.Blue, // 글자 색상
        textAlign = TextAlign.Center // 텍스트 정렬
    )
}