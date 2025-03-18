package com.ymg.architecture.ui.design.component.scaffold

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ymg.architecture.ui.design.action.keyboard.keyboardAsState
import com.ymg.architecture.ui.design.component.snackbar.BaseSnackbar

@Composable
fun BaseScaffold(
    managedKeyboardStateUsed: Boolean,
    backgroundColor: Color,
    snackbarState: SnackbarHostState,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            topBar()
        },
        snackbarHost = {
            BaseSnackbar(
                snackbarState = snackbarState
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        paddingValues = it
                    ),
                color = backgroundColor
            ) {
                if (managedKeyboardStateUsed) {
                    keyboardAsState()
                }
                content()
            }
        }
    )
}
