package com.ymg.intent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MyBroadcastReceiver", "Broadcast received")
        // 여기서 추가적인 처리를 할 수 있다.
    }
}