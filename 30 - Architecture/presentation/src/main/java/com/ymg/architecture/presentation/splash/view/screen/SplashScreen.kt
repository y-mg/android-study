package com.ymg.architecture.presentation.splash.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ymg.architecture.presentation.splash.viewmodel.SplashViewModel
import com.ymg.architecture.ui.core.state.UiStateContainer
import com.ymg.architecture.ui.design.component.column.CenterColumn
import com.ymg.architecture.ui.resource.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel
) {
    val state by viewModel.container.stateFlow.collectAsState()

    UiStateContainer(
        content = {
            Content()
        },
        uiState = state.uiState
    )
}

@Composable
private fun Content() {
    CenterColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Splash Screen 은 WindowsInsets 이 0.dp 이기 때문에 아이콘의 위치를 맞추기 위해서 사용
        Box(
            modifier = Modifier.size(
                size = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
            )
        )
        Image(
            modifier = Modifier.size(
                size = 288.dp
            ),
            painter = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = "App Icon"
        )
    }
}
