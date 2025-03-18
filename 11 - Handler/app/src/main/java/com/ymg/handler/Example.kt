package com.ymg.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class WorkerThread : Thread() {
    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare() // 현재 스레드에서 Looper 준비
        handler = Handler(Looper.myLooper()!!) { message ->
            Log.d("WorkerThread", "Received: ${message.what}")
            true
        }
        Looper.loop() // 메시지 루프 실행
    }

    fun sendMessage(msg: Int) {
        handler.sendMessage(Message.obtain().apply { what = msg })
    }
}

class ExampleHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            1 -> {
                Log.d("Handler", "Message 1 received")
            }
            2 -> {
                Log.d("Handler", "Message 2 received")
            }
            else -> {
                Log.d("Handler", "Unknown message received")
            }
        }
    }
}