package com.ymg.architecture.ui.design.component.topappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ymg.architecture.ui.design.color.customColorScheme
import com.ymg.architecture.ui.design.component.button.icon.IconArrowBackButton
import com.ymg.architecture.ui.design.component.spacer.SpacerWidth
import com.ymg.architecture.ui.design.component.text.Title_1_BoldText

@Composable
fun BaseTopAppBar(
    title: String = "",
    backUsed: Boolean = true,
    onBack: () -> Unit = {},
    menu: @Composable () -> Unit = {},
    backgroundColor: Color = MaterialTheme.customColorScheme.white
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        ),
        title = {
            Title_1_BoldText(
                text = title,
                color = MaterialTheme.customColorScheme.grey800,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            // Default Start Padding 4.dp
            Box(
                modifier = Modifier.padding(
                    start = 20.dp
                )
            ) {
                if (backUsed) {
                    IconArrowBackButton(
                        size = 24.dp,
                        onClick = onBack
                    )
                } else {
                    SpacerWidth(
                        width = 24.dp
                    )
                }
            }
        },
        actions = {
            // Default End Padding 4.dp
            Box(
                modifier = Modifier.padding(
                    end = 20.dp
                )
            ) {
                menu()
            }
        }
    )
}
