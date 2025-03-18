package com.ymg.architecture.feature.photo.detail.viewmodel.state

import com.ymg.architecture.domain.model.PhotoModel
import com.ymg.architecture.ui.core.state.StateUpdatable
import com.ymg.architecture.ui.core.state.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDetailState(
    override val uiState: UiState = UiState.Nothing,
    val photo: PhotoModel = PhotoModel()
): StateUpdatable {
    override fun withUpdatedUiState(
        newState: UiState
    ): StateUpdatable = copy(
        uiState = newState
    )
}