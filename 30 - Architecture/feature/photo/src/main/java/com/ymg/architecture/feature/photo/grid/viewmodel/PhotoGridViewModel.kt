package com.ymg.architecture.feature.photo.grid.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ymg.architecture.domain.remote.usecase.GetPhotosUseCase
import com.ymg.architecture.feature.photo.grid.viewmodel.sideeffect.PhotoGridSideEffect
import com.ymg.architecture.feature.photo.grid.viewmodel.state.PhotoGridState
import com.ymg.architecture.ui.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoGridViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPhotosUseCase: GetPhotosUseCase
) : BaseViewModel<PhotoGridState, PhotoGridSideEffect>(
    initialState = PhotoGridState(),
    savedStateHandle = savedStateHandle
) {
    val photos = getPhotosUseCase().cachedIn(viewModelScope)
}
