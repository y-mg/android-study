package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ExampleAdvancedConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val guideline = createGuidelineFromStart(0.3f) // 특정 위치에 가상의 가이드를 추가하여 정렬
        val text = createRef()

        Text(
            text = "Guideline Example",
            modifier = Modifier.constrainAs(text) {
                start.linkTo(guideline)
                top.linkTo(parent.top, margin = 16.dp)
            }
        )
    }
}