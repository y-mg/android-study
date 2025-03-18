package com.ymg.architecture.feature.photo.grid.viewmodel.state

import com.ymg.architecture.ui.core.state.StateUpdatable
import com.ymg.architecture.ui.core.state.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoGridState(
    override val uiState: UiState = UiState.Nothing
) : StateUpdatable {
    override fun withUpdatedUiState(
        newState: UiState
    ): StateUpdatable = copy(
        uiState = newState
    )
}
