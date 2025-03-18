package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleBoxWithConstraints() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if (maxWidth < 400.dp) {
            Text(
                text = "작은 화면",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Text(
                text = "큰 화면",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}