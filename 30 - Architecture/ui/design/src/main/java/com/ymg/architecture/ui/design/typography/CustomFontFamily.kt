package com.ymg.architecture.ui.design.typography

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ymg.architecture.ui.resource.R

val CustomFontFamily = FontFamily(
    Font(
        resId = R.font.pretendard_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.pretendard_medium,
        weight = FontWeight.Medium
    )
)
