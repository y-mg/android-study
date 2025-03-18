package com.ymg.composable.ui.example

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalCounter = compositionLocalOf { 0 }

@Composable
fun ParentComponent() {
    CompositionLocalProvider(LocalCounter provides 5) {
        ChildComponent()
    }
}

@Composable
fun ChildComponent() {
    Text(
        "Counter: ${LocalCounter.current}"
    )
}