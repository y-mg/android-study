package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExampleSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            scope.launch {
                snackbarHostState.showSnackbar("Snackbar 메시지")
            }
        }) {
            Text("Show Snackbar")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}