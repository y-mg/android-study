package com.ymg.composable.ui.example

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExampleDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "제목")
            },
            text = {
                Text("다이얼로그 내용입니다.")
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("확인")
                }
            }
        )
    }
}