package com.ymg.architecture.ui.core.state

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

sealed class UiState : Parcelable {
    @Parcelize
    data object Nothing : UiState()

    @Parcelize
    data object Loading : UiState()

    @Parcelize
    data object Posting : UiState()

    @Parcelize
    data object Success : UiState()

    @Parcelize
    data class LoadFailure(
        val throwable: Throwable,
        @IgnoredOnParcel val onRetry: (() -> Unit)? = null
    ) : UiState()

    @Parcelize
    data class PostFailure(
        val throwable: Throwable
    ) : UiState()
}
