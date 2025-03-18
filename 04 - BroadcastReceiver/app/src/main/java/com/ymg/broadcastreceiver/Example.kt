package com.ymg.broadcastreceiver

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

val receiver = MyStaticBroadcastReceiver()

// 동적 등록(예: 액티비티에서 등록)
fun exampleRegisterDynamicBroadcastReceiver(context: Context) {
    val filter = IntentFilter(Intent.ACTION_BATTERY_LOW)
    context.registerReceiver(receiver, filter)
}

// 동적 해제(필수)
fun exampleUnRegisterDynamicBroadcastReceiver(context: Context) {
    context.unregisterReceiver(receiver)
}

// 브로드캐스트 전송
fun exampleSendBroadcast(context: Context) {
    val intent = Intent("com.ymg.CUSTOM_ACTION").apply {
        putExtra("message", "Hello, Local Broadcast!")
    }
    LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
}