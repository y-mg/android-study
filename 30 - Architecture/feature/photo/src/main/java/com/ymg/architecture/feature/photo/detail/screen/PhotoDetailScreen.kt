package com.ymg.architecture.feature.photo.detail.screen

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ymg.architecture.feature.photo.detail.viewmodel.PhotoDetailViewModel
import com.ymg.architecture.ui.core.state.UiStateContainer
import com.ymg.architecture.ui.design.component.image.BaseImage
import com.ymg.architecture.ui.design.component.topappbar.BaseTopAppBar

@Composable
fun PhotoDetailScreen(
    navController: NavHostController,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()

    UiStateContainer(
        topBar = {
            BaseTopAppBar(
                onBack = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            Content(
                viewModel = viewModel
            )
        },
        uiState = state.uiState
    )
}

@Composable
private fun Content(
    viewModel: PhotoDetailViewModel
) {
    val state by viewModel.container.stateFlow.collectAsState()

    BaseImage(
        url = state.photo.thumb,
        modifier = Modifier.aspectRatio(1.0f)
    )
}