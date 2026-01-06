package com.ymg.architecture.feature.photo.grid.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ymg.architecture.domain.remote.usecase.GetPhotosUseCase
import com.ymg.architecture.feature.photo.grid.viewmodel.intent.PhotoGridIntent
import com.ymg.architecture.feature.photo.grid.viewmodel.sideeffect.PhotoGridSideEffect
import com.ymg.architecture.feature.photo.grid.viewmodel.state.PhotoGridState
import com.ymg.architecture.ui.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import javax.inject.Inject

@HiltViewModel
class PhotoGridViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPhotosUseCase: GetPhotosUseCase
) : BaseViewModel<PhotoGridState, PhotoGridIntent, PhotoGridSideEffect>(
    initialState = PhotoGridState(),
    savedStateHandle = savedStateHandle
) {
    val photos = getPhotosUseCase().cachedIn(viewModelScope)

    override fun postIntent(intent: PhotoGridIntent) {
        when (intent) {
            is PhotoGridIntent.Nav -> intent {
                postSideEffect(
                    sideEffect = PhotoGridSideEffect.NavigateToDetail(
                        id = intent.id
                    )
                )
            }
        }
    }
}
