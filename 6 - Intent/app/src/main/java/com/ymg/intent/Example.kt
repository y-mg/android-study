package com.ymg.intent

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat

// 명시적 인텐트 예제 - 다른 액티비티로 데이터 전달
fun exampleExplicitIntent(context: Context) {
    val intent = Intent(context, MyActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        putExtra("key", "value")
    }
    context.startActivity(intent)
}

// 알림(Notification)에서 PendingIntent 사용
fun examplePendingIntentWithNotification(context: Context) {
    // 알림 클릭 시 특정 액티비티로 이동하는 예제
    val intent = Intent(context, MyActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // 알림을 생성하고 클릭 시 PendingIntent 실행
    val notification = NotificationCompat.Builder(context, "channel_id")
        .setContentTitle("알림 제목")
        .setContentText("알림 내용")
        .setSmallIcon(android.R.drawable.ic_notification_clear_all)
        .setContentIntent(pendingIntent) // 클릭 시 PendingIntent 실행
        .setAutoCancel(true) // 클릭 후 알림 제거
        .build()

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notification)
}

// 액티비티 실행을 위한 PendingIntent
fun examplePendingIntentWithActivity(context: Context) {
    val intent = Intent(context, MyActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}

// 서비스 실행을 위한 PendingIntent
fun examplePendingIntentWithService(context: Context) {
    val intent = Intent(context, MyService::class.java)
    val pendingIntent = PendingIntent.getService(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}

// 브로드캐스트 리시버 호출을 위한 PendingIntent
fun examplePendingIntentWithBroadcastReceiver(context: Context) {
    val intent = Intent(context, MyBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}