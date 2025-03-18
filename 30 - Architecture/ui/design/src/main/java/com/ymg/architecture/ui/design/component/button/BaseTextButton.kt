package com.ymg.architecture.ui.design.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ymg.architecture.ui.design.action.click.MultipleClickCutter
import com.ymg.architecture.ui.design.action.click.get

@Composable
fun BaseTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit
) {
    val multipleEventCutter = remember {
        MultipleClickCutter.get()
    }

    TextButton(
        onClick = {
            multipleEventCutter.processEvent {
                onClick()
            }
        },
        modifier = modifier.defaultMinSize(
            minWidth = 1.dp,
            minHeight = 1.dp
        ),
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = PaddingValues(0.dp),
        interactionSource = remember {
            MutableInteractionSource()
        },
        content = content
    )
}
