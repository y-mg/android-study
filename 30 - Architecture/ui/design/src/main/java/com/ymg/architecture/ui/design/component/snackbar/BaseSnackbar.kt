package com.ymg.architecture.ui.design.component.snackbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ymg.architecture.ui.design.color.customColorScheme
import com.ymg.architecture.ui.design.component.button.BaseTextButton
import com.ymg.architecture.ui.design.component.column.CenterColumn
import com.ymg.architecture.ui.design.component.text.Body_2_BoldText
import com.ymg.architecture.ui.design.component.text.Body_2_MediumText

@Composable
fun BaseSnackbar(
    snackbarState: SnackbarHostState
) {
    CenterColumn {
        SnackbarHost(
            hostState = snackbarState
        ) { data ->
            Snackbar(
                modifier = Modifier.padding(
                    all = 24.dp
                ),
                action = {
                    BaseTextButton(
                        modifier = Modifier.height(
                            height = 30.dp
                        ),
                        onClick = {
                            data.performAction()
                        }
                    ) {
                        Body_2_BoldText(
                            text = data.visuals.actionLabel ?: "",
                            color = MaterialTheme.customColorScheme.yellow1
                        )
                    }
                },
                shape = RoundedCornerShape(
                    size = 10.dp
                ),
                containerColor = MaterialTheme.customColorScheme.grey800
            ) {
                Body_2_MediumText(
                    text = data.visuals.message,
                    color = MaterialTheme.customColorScheme.white
                )
            }
        }
    }
}
