package com.ymg.architecture.feature.photo.grid.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.ymg.architecture.feature.photo.grid.viewmodel.PhotoGridViewModel
import com.ymg.architecture.feature.photo.grid.viewmodel.intent.PhotoGridIntent
import com.ymg.architecture.feature.photo.grid.viewmodel.sideeffect.PhotoGridSideEffect
import com.ymg.architecture.ui.core.state.UiStatePagingContainer
import com.ymg.architecture.ui.design.component.image.BaseImage
import com.ymg.architecture.ui.navigator.photo.PhotoNavigator.Companion.photoNavigateToDetail
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PhotoGridScreen(
    navController: NavHostController,
    viewModel: PhotoGridViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val items = viewModel.photos.collectAsLazyPagingItems()

    viewModel.collectSideEffect {
        when (it) {
            is PhotoGridSideEffect.NavigateToDetail -> {
                navController.photoNavigateToDetail(
                    id = it.id
                )
            }
        }
    }

    UiStatePagingContainer(
        content = {
            Content(
                viewModel = viewModel
            )
        },
        uiState = state.uiState,
        pagingItems = items
    )
}

@Composable
private fun Content(
    viewModel: PhotoGridViewModel
) {
    val items = viewModel.photos.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(
                count = items.itemCount,
                key = items.itemKey {
                    it.id
                },
                itemContent = { idx ->
                    val item = items[idx]
                    if (item != null) {
                        PhotoGridItem(
                            viewModel = viewModel,
                            id = item.id,
                            url = item.thumb
                        )
                    }
                }
            )

            if (items.loadState.append is LoadState.Loading) {
                item(
                    span = {
                        GridItemSpan(maxCurrentLineSpan)
                    }
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun PhotoGridItem(
    viewModel: PhotoGridViewModel,
    id: String,
    url: String,
) {
    BaseImage(
        url = url,
        modifier = Modifier
            .aspectRatio(1.0f)
            .clickable(
                onClick = {
                    viewModel.postIntent(
                        intent = PhotoGridIntent.Nav(
                            id = id
                        )
                    )
                }
            )
    )
}