package com.ymg.architecture.ui.core.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymg.architecture.domain.remote.api.ApiResult
import com.ymg.architecture.domain.remote.api.onFailure
import com.ymg.architecture.ui.core.state.StateUpdatable
import com.ymg.architecture.ui.core.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<S : StateUpdatable, SE : Any>(
    initialState: S,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<S, SE> {
    override val container = container<S, SE>(
        initialState = initialState,
        savedStateHandle = savedStateHandle,
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                updateState {
                    @Suppress("UNCHECKED_CAST")
                    withUpdatedUiState(
                        newState = UiState.PostFailure(
                            throwable = throwable
                        )
                    ) as S
                }
            }
        }
    )

    protected val currentState: S
        get() = container.stateFlow.value

    protected fun updateState(
        reduce: S.() -> S
    ) = intent {
        reduce {
            state.reduce()
        }
    }

    fun postSideEffect(
        sideEffect: SE
    ) = intent {
        postSideEffect(sideEffect)
    }

    protected fun ioJob(
        coroutineContext: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(coroutineContext) {
        supervisorScope {
            block()
        }
    }

    protected fun <T : Any> Flow<ApiResult<T>>.fetchLoad() {
        ioJob {
            this@fetchLoad.onStart {
                updateState {
                    @Suppress("UNCHECKED_CAST")
                    withUpdatedUiState(
                        newState = UiState.Loading
                    ) as S
                }
            }.onFailure {
                updateState {
                    @Suppress("UNCHECKED_CAST")
                    withUpdatedUiState(
                        newState = UiState.LoadFailure(
                            throwable = it,
                            onRetry = {
                                fetchLoad()
                            }
                        )
                    ) as S
                }
            }.collect()
        }
    }

    protected fun <T : Any> Flow<ApiResult<T>>.fetchPost() {
        ioJob {
            this@fetchPost.onStart {
                updateState {
                    @Suppress("UNCHECKED_CAST")
                    withUpdatedUiState(
                        newState = UiState.Loading
                    ) as S
                }
            }.onFailure {
                updateState {
                    @Suppress("UNCHECKED_CAST")
                    withUpdatedUiState(
                        newState = UiState.PostFailure(
                            throwable = it
                        )
                    ) as S
                }
            }.collect()
        }
    }
}
