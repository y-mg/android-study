package com.ymg.architecture.ui.core.state

import android.os.Parcelable

interface StateUpdatable : Parcelable {
    val uiState: UiState

    fun withUpdatedUiState(
        newState: UiState
    ): StateUpdatable
}
