package com.ymg.architecture.ui.design.component.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.ymg.architecture.ui.resource.R

@Composable
fun BaseImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    url: String
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(
            context = LocalContext.current
        ).data(
            data = url
        ).crossfade(
            durationMillis = 300
        ).memoryCacheKey(
            key = url
        ).memoryCachePolicy(
            policy = CachePolicy.ENABLED
        ).error(
            drawableResId = R.drawable.ic_holder
        ).fallback(
            drawableResId = R.drawable.ic_holder
        ).build(),
        contentScale = contentScale,
        contentDescription = "Network Image"
    )
}
