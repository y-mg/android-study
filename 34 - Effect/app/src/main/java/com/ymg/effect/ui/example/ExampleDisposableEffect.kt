package com.ymg.effect.ui.example

import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.ymg.effect.MyReceiver

@Composable
fun ExampleDisposableEffect() {
    val context = LocalContext.current

    DisposableEffect(context) {
        // MyReceiver 인스턴스 생성
        val receiver = MyReceiver()

        // MY_ACTION 을 수신할 수 있도록 인텐트 필터 등록
        val filter = IntentFilter("MY_ACTION")
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        onDispose {
            // 컴포지션에서 벗어날 때 receiver 해제
            context.unregisterReceiver(receiver)
        }
    }
}