package com.ymg.architecture.presentation.single

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ymg.architecture.presentation.AppNavGraph
import com.ymg.architecture.ui.core.base.BaseActivity
import com.ymg.architecture.ui.design.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleActivity : BaseActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                navController = rememberNavController()
                AppNavGraph(
                    navController = navController
                )
            }
        }
    }
}
