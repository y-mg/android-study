package com.ymg.architecture.presentation.splash.viewmodel.state

import com.ymg.architecture.ui.core.state.StateUpdatable
import com.ymg.architecture.ui.core.state.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SplashState(
    override val uiState: UiState = UiState.Nothing
) : StateUpdatable<SplashState> {
    override fun withUpdatedUiState(
        newState: UiState
    ) = copy(
        uiState = newState
    )
}
