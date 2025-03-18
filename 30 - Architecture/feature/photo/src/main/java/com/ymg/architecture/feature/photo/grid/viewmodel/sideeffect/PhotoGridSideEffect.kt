package com.ymg.architecture.feature.photo.grid.viewmodel.sideeffect

sealed class PhotoGridSideEffect {
    data class NavigateToDetail(
        val id: String
    ) : PhotoGridSideEffect()
}
