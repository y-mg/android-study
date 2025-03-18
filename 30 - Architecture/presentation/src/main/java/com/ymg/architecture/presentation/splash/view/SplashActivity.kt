package com.ymg.architecture.presentation.splash.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ymg.architecture.presentation.single.SingleActivity
import com.ymg.architecture.presentation.splash.view.screen.SplashScreen
import com.ymg.architecture.presentation.splash.viewmodel.SplashViewModel
import com.ymg.architecture.presentation.splash.viewmodel.sideeffect.SplashSideEffect
import com.ymg.architecture.ui.core.base.BaseActivity
import com.ymg.architecture.ui.core.state.UiState
import com.ymg.architecture.ui.design.theme.AppTheme
import com.ymg.architecture.util.android.extension.startActivity
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SplashScreen(
                    viewModel = viewModel
                )
            }
        }
        splashScreen.setKeepOnScreenCondition {
            viewModel.container.stateFlow.value.uiState !is UiState.LoadFailure
        }
        viewModel.observe(
            lifecycleOwner = this,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleSideEffect(
        sideEffect: SplashSideEffect
    ) {
        when (sideEffect) {
            is SplashSideEffect.StartApp -> {
                startActivity<SingleActivity>(
                    isFinishAffinity = true
                )
            }
        }
    }
}
