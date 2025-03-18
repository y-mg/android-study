package com.ymg.architecture.ui.design.component.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

class BaseSnackbarVisuals(
    override val actionLabel: String,
    override val duration: SnackbarDuration = SnackbarDuration.Indefinite,
    override val message: String,
    override val withDismissAction: Boolean = true
) : SnackbarVisuals
