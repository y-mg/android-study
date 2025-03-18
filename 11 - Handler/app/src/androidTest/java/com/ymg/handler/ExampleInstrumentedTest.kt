package com.ymg.handler

import android.os.HandlerThread
import android.os.Message
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun `Looper_동작_확인`() {
        val workerThread = WorkerThread()
        workerThread.start()

        // WorkerThread 가 초기화될 때까지 대기
        Thread.sleep(500)

        // 메시지를 보내기
        workerThread.sendMessage(1)

        // 메시지가 처리될 시간을 기다림(테스트용)
        Thread.sleep(500)
    }

    @Test
    fun `Handler_메시지_전송`() {
        val handlerThread = HandlerThread("TestHandlerThread").apply { start() }
        val handler = ExampleHandler(handlerThread.looper)

        // 메시지를 보냄
        val message = Message.obtain().apply { what = 1 }
        handler.sendMessage(message)

        // 메시지가 처리될 시간을 기다림(테스트용)
        Thread.sleep(500)
    }
}