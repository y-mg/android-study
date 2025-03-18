package com.ymg.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

// 포그라운드 서비스 시작
fun exampleStartForegroundService(context: Context) {
    val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
        action = MyForegroundService.START_SERVICE
    }
    ContextCompat.startForegroundService(context, serviceIntent)
}

// 포그라운드 서비스 중지
fun exampleStopForegroundService(context: Context) {
    val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
        action = MyForegroundService.STOP_SERVICE
    }
    ContextCompat.startForegroundService(context, serviceIntent)
}

// WorkManager 시작
fun exampleStartWorker(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
    WorkManager.getInstance(context).enqueue(workRequest)
}