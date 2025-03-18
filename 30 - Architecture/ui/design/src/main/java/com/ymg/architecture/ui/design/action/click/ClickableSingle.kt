package com.ymg.architecture.ui.design.action.click

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

inline fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    rippleUsed: Boolean = false,
    crossinline onClick: () -> Unit
) = composed {
    val multipleClickCutter = remember {
        MultipleClickCutter.get()
    }

    Modifier.clickable(
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = if (rippleUsed) {
            LocalIndication.current
        } else {
            null
        },
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = {
            multipleClickCutter.processEvent {
                onClick()
            }
        }
    )
}
