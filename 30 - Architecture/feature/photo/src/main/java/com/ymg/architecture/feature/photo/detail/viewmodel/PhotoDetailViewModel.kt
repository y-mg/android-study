package com.ymg.architecture.feature.photo.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.ymg.architecture.domain.remote.api.onSuccess
import com.ymg.architecture.domain.remote.usecase.GetPhotoUseCase
import com.ymg.architecture.feature.photo.detail.viewmodel.sideeffect.PhotoDetailSideEffect
import com.ymg.architecture.feature.photo.detail.viewmodel.state.PhotoDetailState
import com.ymg.architecture.ui.core.base.BaseViewModel
import com.ymg.architecture.ui.core.state.UiState
import com.ymg.architecture.ui.navigator.NavigatorConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotoUseCase: GetPhotoUseCase
) : BaseViewModel<PhotoDetailState, PhotoDetailSideEffect>(
    initialState = PhotoDetailState(),
    savedStateHandle = savedStateHandle
) {
    init {
        fetchGetPhoto()
    }

    private fun fetchGetPhoto() = getPhotoUseCase(
        id = savedStateHandle.getStateFlow(NavigatorConstant.KEY_ID, "").value
    ).onSuccess {
        updateState {
            copy(
                uiState = UiState.Success,
                photo = it
            )
        }
    }.fetchLoad()
}