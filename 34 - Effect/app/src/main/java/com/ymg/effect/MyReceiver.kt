package com.ymg.effect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    // onReceive 메서드는 브로드캐스트를 수신할 때 호출된다.
    override fun onReceive(context: Context, intent: Intent) {
        // 수신한 인텐트의 액션을 확인
        val action = intent.action
        if (action == "MY_ACTION") {
            // "MY_ACTION" 액션이 수신되면 처리할 작업을 정의한다.
            Log.d("MyReceiver", "MY_ACTION received!")
            
            // 예를 들어, 수신된 데이터 처리
            val data = intent.getStringExtra("data_key")
            Log.d("MyReceiver", "Received data: $data")
        }
    }
}