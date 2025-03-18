package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExampleCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue // 카드의 배경색을 설정할 수 있음
        ),
        shape = RoundedCornerShape(8.dp), // 모서리를 둥글게 처리
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp // 그림자의 높이를 조절하여 입체감을 조정할 수 있음
        )
    ) {
        Text(
            text = "Hello, Card!",
            modifier = Modifier.padding(16.dp)
        )
    }
}