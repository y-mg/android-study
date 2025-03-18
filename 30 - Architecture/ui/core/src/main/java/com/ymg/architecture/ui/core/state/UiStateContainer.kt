package com.ymg.architecture.ui.core.state

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.ymg.architecture.ui.design.color.customColorScheme
import com.ymg.architecture.ui.design.component.progress.DialogProgress
import com.ymg.architecture.ui.design.component.scaffold.BaseScaffold

@Composable
fun UiStateContainer(
    managedKeyboardStateUsed: Boolean = false,
    backgroundColor: Color = MaterialTheme.customColorScheme.white,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    uiState: UiState
) {
    var showPostingProgress by remember {
        mutableStateOf(false)
    }
    val snackbarState = remember {
        SnackbarHostState()
    }

    showPostingProgress = uiState == UiState.Posting
    if (showPostingProgress) {
        DialogProgress(
            onDismiss = {
                showPostingProgress = false
            }
        )
    }

    when (uiState) {
        is UiState.LoadFailure -> {
            HandleFailure(
                uiStateLoadFailure = uiState,
                snackbarState = snackbarState
            )
        }

        is UiState.PostFailure -> {
            HandleFailure(
                uiStatePostFailure = uiState,
                snackbarState = snackbarState
            )
        }

        else -> Unit
    }

    BaseScaffold(
        managedKeyboardStateUsed = managedKeyboardStateUsed,
        backgroundColor = backgroundColor,
        snackbarState = snackbarState,
        topBar = topBar,
        content = content
    )
}
