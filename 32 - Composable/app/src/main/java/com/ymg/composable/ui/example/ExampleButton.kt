package com.ymg.composable.ui.example

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ExampleButton() {
    Button(
        onClick = { println("Button Clicked") }, // 클릭 이벤트
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue, // 버튼 배경색 지정
            contentColor = Color.White // 버튼 안의 텍스트 색상 변경
        )
    ) {
        Text(text = "Click Me")
    }
}