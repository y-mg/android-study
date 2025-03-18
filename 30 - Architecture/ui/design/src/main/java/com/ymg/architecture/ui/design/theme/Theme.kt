package com.ymg.architecture.ui.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.ymg.architecture.ui.design.color.DarkCustomColorScheme
import com.ymg.architecture.ui.design.color.LightCustomColorScheme
import com.ymg.architecture.ui.design.color.LocalCustomColorScheme
import com.ymg.architecture.ui.design.typography.CustomPretendardTypography
import com.ymg.architecture.ui.design.typography.LocalCustomTypography

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) {
                dynamicDarkColorScheme(
                    context = context
                )
            } else {
                dynamicLightColorScheme(
                    context = context
                )
            }
        }

        isDarkTheme -> {
            darkColorScheme()
        }

        else -> {
            lightColorScheme()
        }
    }
    val typography = MaterialTheme.typography

    val customColorScheme = if (isDarkTheme) {
        DarkCustomColorScheme
    } else {
        LightCustomColorScheme
    }

    val customTypography = CustomPretendardTypography

    CompositionLocalProvider(
        LocalCustomColorScheme provides customColorScheme,
        LocalCustomTypography provides customTypography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}
