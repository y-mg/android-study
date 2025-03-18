package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Example(
    val id: Int,
    val name: String
)

@Composable
fun ExampleLazyColumn() {
    val items = List(100) {
        Example(
            id = it,
            name = "$it"
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = items,
            key = {
                it.id
            }
        ) { item ->
            Text(
                text = item.name,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}