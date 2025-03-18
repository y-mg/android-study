package com.ymg.composable.ui.example

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleTopAppBar() {
    TopAppBar(
        title = { // 앱 바의 제목을 설정
            Text("My App")
        },
        navigationIcon = { // 좌측에 배치할 네비게이션 아이콘
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Menu"
                )
            }
        },
        actions = { // 우측에 배치할 액션 버튼을 설정
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}