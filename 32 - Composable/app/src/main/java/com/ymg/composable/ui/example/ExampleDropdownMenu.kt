package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ExampleDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        "Option 1",
        "Option 2",
        "Option 3"
    )

    Box {
        Button(
            onClick = {
                expanded = true
            }
        ) {
            Text("메뉴 열기")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item)
                    },
                    onClick = {
                        expanded = false
                    }
                )
            }
        }
    }
}