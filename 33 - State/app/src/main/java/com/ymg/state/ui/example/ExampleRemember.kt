package com.ymg.state.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleNoRemember() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        // 상태가 없으므로 입력한 텍스트가 유지되지 않음
        Text(text = "Hello")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text("Name")
            }
        )
    }
}

@Composable
fun ExampleRemember() {
    Column(modifier = Modifier.padding(10.dp)) {
        // remember 를 사용해 상태를 저장하고 유지
        var name by remember { mutableStateOf("") }

        Text(text = "Hello")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}