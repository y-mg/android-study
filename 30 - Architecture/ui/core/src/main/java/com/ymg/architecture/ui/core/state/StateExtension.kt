package com.ymg.architecture.ui.core.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import com.ymg.architecture.domain.remote.api.exception.ApiException
import com.ymg.architecture.domain.remote.api.exception.CommonException
import com.ymg.architecture.ui.design.component.snackbar.BaseSnackbarVisuals
import com.ymg.architecture.ui.resource.R

@Composable
internal fun HandleFailure(
    uiStateLoadFailure: UiState.LoadFailure,
    snackbarState: SnackbarHostState
) {
    val message = uiStateLoadFailure.throwable.handleErrorMessage()
    val actionLabel = stringResource(
        id = R.string.retry
    )
    LaunchedEffect(Unit) {
        snackbarState.showSnackbar(
            BaseSnackbarVisuals(
                message = message,
                actionLabel = actionLabel
            )
        ).run {
            if (this == SnackbarResult.ActionPerformed) {
                uiStateLoadFailure.onRetry?.invoke()
            }
        }
    }
}

@Composable
internal fun HandleFailure(
    uiStatePostFailure: UiState.PostFailure,
    snackbarState: SnackbarHostState
) {
    val message = uiStatePostFailure.throwable.handleErrorMessage()
    val actionLabel = stringResource(
        id = R.string.confirm
    )
    LaunchedEffect(Unit) {
        snackbarState.showSnackbar(
            BaseSnackbarVisuals(
                message = message,
                actionLabel = actionLabel
            )
        )
    }
}

@Composable
internal fun HandlePagingError(
    loadStateError: LoadState.Error,
    snackbarState: SnackbarHostState,
    onRetry: () -> Unit
) {
    val message = loadStateError.error.handleErrorMessage()
    val actionLabel = stringResource(
        id = R.string.retry
    )
    LaunchedEffect(Unit) {
        snackbarState.showSnackbar(
            BaseSnackbarVisuals(
                message = message,
                actionLabel = actionLabel
            )
        ).run {
            if (this == SnackbarResult.ActionPerformed) {
                onRetry()
            }
        }
    }
}

@Composable
private fun Throwable.handleErrorMessage(): String = when (this) {
    is CommonException.NetworkException -> {
        stringResource(
            id = R.string.error_network
        )
    }

    is CommonException.ServerException -> {
        stringResource(
            id = R.string.error_server
        )
    }

    is CommonException.UnknownException -> {
        stringResource(
            id = R.string.error_unknown
        )
    }

    is ApiException -> {
        message
    }

    else -> {
        stringResource(
            id = R.string.error_unknown
        )
    }
}
