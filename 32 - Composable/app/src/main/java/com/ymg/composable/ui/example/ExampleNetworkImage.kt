package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ExampleNetworkImage() {
    AsyncImage(
        model = "https://assets-prd.ignimgs.com/2024/06/12/new-mh-wilds-blog-1718169422801.jpg",
        contentDescription = "Network Image",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}