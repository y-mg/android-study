package com.ymg.architecture.ui.core.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ymg.architecture.domain.remote.api.ApiResult
import com.ymg.architecture.domain.remote.api.onFailure
import com.ymg.architecture.ui.core.state.StateUpdatable
import com.ymg.architecture.ui.core.state.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<S : StateUpdatable<S>, I : Any, SE : Any>(
    initialState: S,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<S, SE> {
    override val container = container<S, SE>(
        initialState = initialState,
        savedStateHandle = savedStateHandle,
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    reduce {
                        state.withUpdatedUiState(
                            newState = UiState.PostFailure(
                                throwable = throwable
                            )
                        )
                    }
                }
            }
        }
    )

    abstract fun postIntent(intent: I)

    protected fun <T : Any> Flow<ApiResult<T>>.fetchLoad() {
        intent {
            this@fetchLoad.onStart {
                reduce {
                    state.withUpdatedUiState(
                        newState = UiState.Loading
                    )
                }
            }.onFailure {
                reduce {
                    state.withUpdatedUiState(
                        newState = UiState.LoadFailure(
                            throwable = it,
                            onRetry = {
                                fetchLoad()
                            }
                        )
                    )
                }
            }.collect()
        }
    }

    protected fun <T : Any> Flow<ApiResult<T>>.fetchPost() {
        intent {
            this@fetchPost.onStart {
                reduce {
                    state.withUpdatedUiState(
                        newState = UiState.Loading
                    )
                }
            }.onFailure {
                reduce {
                    state.withUpdatedUiState(
                        newState = UiState.PostFailure(
                            throwable = it
                        )
                    )
                }
            }.collect()
        }
    }
}
