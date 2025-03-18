package com.ymg.architecture.ui.design.color

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    val transperent: Color = Color.Unspecified,
    val yellow1: Color = Color.Unspecified,
    val yellow2: Color = Color.Unspecified,
    val yellow3: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val grey50: Color = Color.Unspecified,
    val grey100: Color = Color.Unspecified,
    val grey200: Color = Color.Unspecified,
    val grey300: Color = Color.Unspecified,
    val grey400: Color = Color.Unspecified,
    val grey500: Color = Color.Unspecified,
    val grey600: Color = Color.Unspecified,
    val grey700: Color = Color.Unspecified,
    val grey800: Color = Color.Unspecified,
    val green500: Color = Color.Unspecified,
    val green600: Color = Color.Unspecified,
    val green700: Color = Color.Unspecified,
    val green800: Color = Color.Unspecified,
    val green900: Color = Color.Unspecified,
    val red200: Color = Color.Unspecified,
    val red500: Color = Color.Unspecified,
    val navy: Color = Color.Unspecified,
    val orange: Color = Color.Unspecified
)

val LocalCustomColorScheme = staticCompositionLocalOf {
    CustomColorScheme()
}

val MaterialTheme.customColorScheme: CustomColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorScheme.current
