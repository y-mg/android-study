package com.ymg.mvi.viewmodel

import androidx.lifecycle.ViewModel
import com.ymg.mvi.viewmodel.sideeffect.HomeSideEffect
import com.ymg.mvi.viewmodel.state.HomeState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel : ViewModel(), ContainerHost<HomeState, HomeSideEffect> {
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    // add 함수를 intent 에 담고 연산 결과를 reduce 를 통해 새로운 state 로 생성
    fun add(num: Int) = intent {
        reduce {
            state.copy(
                num = state.num + num
            )
        }
        postSideEffect(HomeSideEffect.Toast("숫자: ${state.num}"))
    }
}