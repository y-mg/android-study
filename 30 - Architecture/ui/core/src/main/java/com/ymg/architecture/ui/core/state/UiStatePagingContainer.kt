package com.ymg.architecture.ui.core.state

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ymg.architecture.ui.design.color.customColorScheme
import com.ymg.architecture.ui.design.component.progress.DialogProgress
import com.ymg.architecture.ui.design.component.scaffold.BaseScaffold

@Composable
fun UiStatePagingContainer(
    managedKeyboardStateUsed: Boolean = false,
    backgroundColor: Color = MaterialTheme.customColorScheme.white,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    uiState: UiState,
    pagingItems: LazyPagingItems<out Any>
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

    if (pagingItems.loadState.refresh is LoadState.Error) {
        HandlePagingError(
            loadStateError = pagingItems.loadState.refresh as LoadState.Error,
            snackbarState = snackbarState
        ) {
            pagingItems.refresh()
        }
    }

    if (pagingItems.loadState.append is LoadState.Error) {
        HandlePagingError(
            loadStateError = pagingItems.loadState.append as LoadState.Error,
            snackbarState = snackbarState
        ) {
            pagingItems.retry()
        }
    }

    BaseScaffold(
        managedKeyboardStateUsed = managedKeyboardStateUsed,
        backgroundColor = backgroundColor,
        snackbarState = snackbarState,
        topBar = topBar,
        content = content
    )
}
