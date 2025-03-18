package com.ymg.architecture.ui.design.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class CustomTypography(
    val pretendardBoldTextStyle: TextStyle = TextStyle(),
    val pretendardMediumTextStyle: TextStyle = TextStyle()
)

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography()
}

val MaterialTheme.customTypogrpahy: CustomTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomTypography.current
