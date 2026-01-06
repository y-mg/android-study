package com.ymg.architecture.feature.photo.grid.viewmodel.intent

sealed class PhotoGridIntent {
    data class Nav(
        val id: String
    ) : PhotoGridIntent()
}