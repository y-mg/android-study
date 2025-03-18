package com.ymg.architecture.ui.core.base

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController

abstract class BaseActivity : ComponentActivity() {
    private val isDarkTheme by lazy {
        (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }

    private val systemColor by lazy {
        if (isDarkTheme) {
            ContextCompat.getColor(this, android.R.color.white)
        } else {
            ContextCompat.getColor(this, android.R.color.white)
        }
    }

    private val systemBarStyle by lazy {
        if (isDarkTheme) {
            SystemBarStyle.light(systemColor, systemColor)
        } else {
            SystemBarStyle.light(systemColor, systemColor)
        }
    }

    protected lateinit var navController: NavHostController

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(
            statusBarStyle = systemBarStyle,
            navigationBarStyle = systemBarStyle
        )
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(Color.BLACK)
        actionBar?.hide()
    }
}
