package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExampleBottomAppBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "홈"
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "즐겨찾기"
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("BottomAppBar 예제")
        }
    }
}