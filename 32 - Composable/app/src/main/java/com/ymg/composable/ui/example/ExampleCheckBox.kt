package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun ExampleCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked, // 체크박스의 현재 상태
            onCheckedChange = { // 체크 상태 변경 시 호출되는 콜백 함수
                checked = it
            }
        )
        Text(
            text = if (checked) {
                "Checked"
            } else {
                "Unchecked"
            }
        )
    }
}