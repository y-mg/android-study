![image](./android.png)
# Service
`Service` 는 백그라운드에서 실행되는 컴포넌트로, UI 와 직접적인 상호작용 없이 장기 실행 작업을 처리하는 데 사용된다.<br/>
그러나 서비스는 기본적으로 메인 스레드에서 실행되므로, 장기 실행 작업은 별도의 스레드나 코루틴을 사용하여 비동기적으로 처리해야 한다.<br/>
<br/>
<br/>

## Foreground Service
Foreground Service 는 사용자가 인지할 수 있도록 알림(Notification)을 제공하는 서비스이다.<br/>
예를 들어 음악 재생, 위치 추적, 파일 다운로드 등의 작업에 사용된다.<br/>

```kotlin
class MyForegroundService : Service() {
    companion object {
        private const val CHANNEL_ID = "MY_FOREGROUND_SERVICE"
        private const val NOTIFICATION_ID = 1
        const val START_SERVICE = "START_MY_FOREGROUND_SERVICE"
        const val STOP_SERVICE = "STOP_MY_FOREGROUND_SERVICE"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_SERVICE -> {
                startService()
            }

            STOP_SERVICE -> {
                stopService()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("This service is running in the foreground")
            .setSmallIcon(android.R.drawable.ic_notification_clear_all)
            .build()
    }

    @SuppressLint("ForegroundServiceType")
    private fun startService() {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun stopService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
        stopSelf()
    }
}
```
```kotlin
// 포그라운드 서비스 시작
fun exampleStartForegroundService(context: Context) {
    val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
        action = MyForegroundService.START_SERVICE
    }
    ContextCompat.startForegroundService(context, serviceIntent)
}
```
```kotlin
// 포그라운드 서비스 중지
fun exampleStopForegroundService(context: Context) {
    val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
        action = MyForegroundService.STOP_SERVICE
    }
    ContextCompat.startForegroundService(context, serviceIntent)
}
```
<br/>
<br/>

## Background Service
Background Service 는 사용자에게 직접적으로 노출되지 않고 백그라운드에서 실행된다.<br/>
하지만 Android 8(API 26) 이상에서는 제한이 많아 백그라운드 서비스의 사용이 권장되지 않으며, 대신 `WorkManager` 또는 `JobScheduler` 를 활용하는 것이 권장된다.<br/>

```kotlin
class MyWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        // 백그라운드 작업 실행
        return Result.success()
    }
}
```
```kotlin
// WorkManager 시작
fun exampleStartWorker(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
    WorkManager.getInstance(context).enqueue(workRequest)
}
```
<br/>
<br/>

## Bind Service
Bind Service 는 특정 컴포넌트(예: `Activity`, `Fragment`)와 바인딩되어 상호작용할 수 있는 서비스이다.<br/>

```kotlin
class MyBoundService : Service() {
    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder = binder

    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }
}
```
```kotlin
class MainActivity : AppCompatActivity() {
    private var myService: MyBoundService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundService.LocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            myService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyBoundService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}
```