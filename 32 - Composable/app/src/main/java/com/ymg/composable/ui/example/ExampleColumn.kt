package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExampleColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly, // 정렬 방식을 지정
        horizontalAlignment = Alignment.CenterHorizontally // 자식 요소의 가로 정렬을 조정
    ) {
        Text(
            text = "Item 1",
            color = Color.Black
        )
        Text(
            text = "Item 2",
            color = Color.Blue
        )
        Text(
            text = "Item 3",
            color = Color.Red
        )
    }
}