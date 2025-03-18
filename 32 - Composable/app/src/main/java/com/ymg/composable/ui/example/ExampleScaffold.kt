package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleScaffold() {
    Scaffold(
        topBar = { // 상단 앱 바를 설정
            TopAppBar(
                title = {
                    Text("Scaffold Example")
                }
            )
        },
        floatingActionButton = { // 플로팅 액션 버튼
            FloatingActionButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        // 메인 컨텐츠 영역
        Text(
            "Content here",
            modifier = Modifier.padding(paddingValues)
        )
    }
}