package com.ymg.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        // 백그라운드 작업 실행
        return Result.success()
    }
}