package com.ymg.composable.ui.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ExampleImage() {
    Image(
        painter = painterResource( // 로컬 리소스를 로드
            id = android.R.drawable.stat_notify_sdcard
        ),
        contentDescription = "Sample Image",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp)), // 둥근 모서리로 스타일링
        contentScale = ContentScale.Crop // 이미지 크기 조정 방식을 지정
    )
}