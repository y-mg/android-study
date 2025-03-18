![banner](./android.png)
# Intent
- - -
`Intent` 는 안드로이드에서 컴포넌트 간의 통신을 담당하는 핵심 메커니즘이다.<br/> 
이를 통해 다른 컴포넌트를 실행하거나 데이터를 전달할 수 있다.<br/>
<br/>
<br/>

## 명시적 인텐트(Explicit Intent)
명시적 인텐트는 대상 컴포넌트의 클래스 이름을 직접 지정하여 해당 컴포넌트를 실행하는 방식이다.<br/>
주로 같은 애플리케이션 내에서 사용된다.<br/>

```kotlin
// 명시적 인텐트 예제 - 다른 액티비티로 데이터 전달
fun exampleExplicitIntent(context: Context) {
    val intent = Intent(context, MyActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        putExtra("key", "value")
    }
    context.startActivity(intent)
}
```
<br/>
<br/>

## 암시적 인텐트(Implicit Intent)
암시적 인텐트는 특정 작업을 수행하도록 요청하지만, 대상 컴포넌트를 직접 지정하지 않는다.<br/>
시스템은 해당 작업을 처리할 수 있는 적절한 컴포넌트를 찾아 실행한다.<br/>

```kotlin
 @Test
fun `암시적_인텐트`() {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}
```
<br/>
<br/>
<br/>



# PendingIntent
- - -
`PendingIntent` 는 나중에 실행될 인텐트를 미리 설정하여 시스템이나 다른 애플리케이션이 대신 실행할 수 있도록 하는 메커니즘이다.<br/> 
주로 알림(Notification), 알람(Alarm), 위젯(Widget) 등의 기능에서 사용된다.<br/>
<br/>
<br/>

##  사용 사례
### 알림(Notification)
알림을 클릭했을 때 특정 화면으로 이동하는 데 사용된다.<br/>
<br/>

### 알람(Alarm)
일정 시간 후 특정 작업을 실행하는 데 사용된다.<br/>
<br/>

### 위젯(Widget)
위젯에서 특정 작업을 실행할 때 사용된다.<br/>
<br/>

### 예제 코드
```kotlin
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
```
<br/>
<br/>

## PendingIntent 생성 방법
```kotlin
// 액티비티 실행을 위한 PendingIntent
fun examplePendingIntentWithActivity(context: Context) {
    val intent = Intent(context, MyActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}
```
```kotlin
// 서비스 실행을 위한 PendingIntent
fun examplePendingIntentWithService(context: Context) {
    val intent = Intent(context, MyService::class.java)
    val pendingIntent = PendingIntent.getService(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}
```
```kotlin
// 브로드캐스트 리시버 호출을 위한 PendingIntent
fun examplePendingIntentWithBroadcastReceiver(context: Context) {
    val intent = Intent(context, MyBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}
```
<br/>
<br/>

## 플래그 및 요청 코드
### 요청 코드(Request Code)
`PendingIntent` 를 여러 개 생성할 경우, 요청 코드(requestCode)를 다르게 설정하면 서로 다른 `PendingIntent` 를 구분할 수 있다.<br/>
<br/>

### 플래그(Flags)
#### PendingIntent.FLAG_UPDATE_CURRENT
기존 `PendingIntent` 를 업데이트한다.<br/>
<br/>

#### PendingIntent.FLAG_CANCEL_CURRENT
기존 `PendingIntent` 를 취소하고 새로 생성한다.<br/>
<br/>

#### PendingIntent.FLAG_NO_CREATE
기존 `PendingIntent` 가 없을 경우 `null` 을 반환한다.<br/>
<br/>

#### PendingIntent.FLAG_ONE_SHOT
`PendingIntent` 를 한 번만 실행 가능하게 하며, 실행 후 자동으로 무효화된다.<br/>
<br/>

#### PendingIntent.FLAG_IMMUTABLE
`PendingIntent` 의 내용을 변경할 수 없도록 설정한다. (API 31 이상 필수)<br/>
<br/>

#### PendingIntent.FLAG_MUTABLE
`PendingIntent` 의 내용을 변경 가능하도록 설정한다.<br/>
<br/>
<br/>

## 주의사항
### 재사용 이슈
동일한 `PendingIntent` 가 재사용될 경우 예상치 못한 동작이 발생할 수 있다.<br/>
이를 방지하려면 `PendingIntent.FLAG_UPDATE_CURRENT` 또는 `PendingIntent.FLAG_CANCEL_CURRENT` 를 적절히 설정해야 한다.<br/>
<br/>
<br/>

### 보안 고려
`PendingIntent` 는 다른 애플리케이션에서도 실행될 수 있기 때문에 보안상의 문제를 유발할 수 있어, 민감한 작업을 처리할 때는 `PendingIntent.FLAG_IMMUTABLE` 을 설정하는 것이 권장된다.<br/>
Android 12(API 31)부터는 `PendingIntent` 생성 시 `PendingIntent.FLAG_IMMUTABLE` 또는 `PendingIntent.FLAG_MUTABLE` 플래그를 반드시 설정해야 한다.<br/>