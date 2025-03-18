package com.ymg.mvi.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.ymg.mvi.viewmodel.HomeViewModel
import com.ymg.mvi.viewmodel.sideeffect.HomeSideEffect
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    viewModel: HomeViewModel
) {
    val state by viewModel.container.stateFlow.collectAsState()

    // Side Effect 실행
    viewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.Toast -> {
                Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column {
        Button(
            onClick = { viewModel.add(1) }
        ) {
            Text("Add")
        }
        Text("숫자: ${state.num}")
    }
}