package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ExampleConstraintSet() {
    val constraints = ConstraintSet {
        val text = createRefFor("text")
        val button = createRefFor("button")

        constrain(text) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(button) {
            top.linkTo(text.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello, Compose!",
            modifier = Modifier.layoutId("text")
        )

        Button(
            onClick = {

            },
            modifier = Modifier.layoutId("button")
        ) {
            Text("클릭")
        }
    }
}