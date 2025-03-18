package com.ymg.architecture.presentation.splash.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.ymg.architecture.domain.local.usecase.SetAccessTokenUseCase
import com.ymg.architecture.presentation.splash.viewmodel.sideeffect.SplashSideEffect
import com.ymg.architecture.presentation.splash.viewmodel.state.SplashState
import com.ymg.architecture.ui.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val setAccessTokenUseCase: SetAccessTokenUseCase
) : BaseViewModel<SplashState, SplashSideEffect>(
    initialState = SplashState(),
    savedStateHandle = savedStateHandle
) {
    init {
        fetchSignIn()
    }

    // 로그인 이후 토큰 저장한다고 가정
    private fun fetchSignIn() = ioJob {
        setAccessTokenUseCase(
            accessToken = "Client-ID hhSKor6cQNByhhQg7eTlEWYkEpYSRITjbjiglqP3xis"
        )
        delay(1000)
        postSideEffect(
            sideEffect = SplashSideEffect.StartApp
        )
    }
}
