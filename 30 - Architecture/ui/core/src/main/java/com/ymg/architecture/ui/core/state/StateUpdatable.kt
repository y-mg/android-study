package com.ymg.architecture.ui.core.state

import android.os.Parcelable

interface StateUpdatable<T : StateUpdatable<T>> : Parcelable {
    val uiState: UiState

    fun withUpdatedUiState(
        newState: UiState
    ): T
}
