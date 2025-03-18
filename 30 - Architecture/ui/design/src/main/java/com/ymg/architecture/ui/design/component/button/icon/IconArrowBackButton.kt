package com.ymg.architecture.ui.design.component.button.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.ymg.architecture.ui.design.action.click.clickableSingle
import com.ymg.architecture.ui.design.color.customColorScheme
import com.ymg.architecture.ui.resource.R

@Composable
fun IconArrowBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentAlignment: Alignment = Alignment.Center,
    size: Dp,
    tint: Color = MaterialTheme.customColorScheme.grey800
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        Icon(
            modifier = Modifier.size(
                size = size
            ).clickableSingle {
                onClick()
            },
            painter = painterResource(
                id = R.drawable.ic_arrow_back
            ),
            tint = tint,
            contentDescription = "Arrow Back Button"
        )
    }
}
