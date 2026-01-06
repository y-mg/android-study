package com.ymg.architecture.ui.design.action.keyboard

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun KeyboardAsState() {
    val focusManager = LocalFocusManager.current
    val density = LocalDensity.current

    val keyboardHeight = WindowInsets.ime.getBottom(density)
    val wasKeyboardVisible = remember { mutableStateOf(keyboardHeight > 0) }

    LaunchedEffect(keyboardHeight) {
        val isKeyboardVisible = keyboardHeight > 0

        if (wasKeyboardVisible.value && !isKeyboardVisible) focusManager.clearFocus()
        wasKeyboardVisible.value = isKeyboardVisible
    }
}
