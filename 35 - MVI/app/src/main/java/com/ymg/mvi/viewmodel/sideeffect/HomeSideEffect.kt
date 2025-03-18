package com.ymg.mvi.viewmodel.sideeffect

// Side Effect 정의
sealed class HomeSideEffect {
    data class Toast(val text: String) : HomeSideEffect()
}