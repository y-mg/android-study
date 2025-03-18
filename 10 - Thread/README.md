![banner](./android.png)
# Thread
- - -
프로세스(Process)는 실행 중인 프로그램의 인스턴스로, 운영체제에서 별도의 메모리 공간을 할당받아 독립적으로 실행된다.<br/>
스레드(Thread)는 프로세스 내에서 실행되는 최소 실행 단위로, 여러 작업을 병렬적으로 수행할 수 있도록 프로세스 내에서 다수의 스레드를 생성할 수 있다.<br/>
즉, 하나의 프로세스 안에 여러 개의 스레드가 존재하며 각 스레드는 독립적으로 작업을 처리할 수 있지만, 같은 메모리 공간을 공유한다.<br/>
안드로이드에서는 크게 메인(UI) 스레드와 백그라운드 스레드로 나눌 수 있으며, 기본적으로 모든 UI 관련 작업은 메인 스레드에서 실행된다.<br/>
그러나 메인 스레드에서 네트워킹, 데이터베이스 액세스와 같은 시간이 오래 걸리는 작업을 수행하면 앱이 느려지거나 응답하지 않는 상태(ANR: Android Not Responding)가 발생할 수 있다.<br/>
이를 방지하기 위해, 백그라운드 스레드를 사용하여 장시간 걸리는 작업을 처리해야 한다.<br/>
<br/>
<br/>

## ANR(Android Not Responding)
ANR 은 앱이 포그라운드 상태일 때 사용자의 클릭, 터치 등의 이벤트에 5초 이내로 응답하지 못하는 경우 발생한다.<br/>
이는 사용자가 앱이 비정상적으로 동작한다고 인식하게 만들기 때문에 주의해야 한다.<br/>
따라서 네트워크 요청, 파일 I/O 작업, 대규모 데이터 처리 등의 작업은 반드시 백그라운드에서 실행해야 한다.<br/>
<br/>
<br/>

## 백그라운드 작업 처리 방법
### Thread / Runnable
단순한 스레드 생성 방법으로, 복잡한 처리는 어렵지만 기본적인 백그라운드 작업에 사용될 수 있다.<br/>

```kotlin
class MyThread : Thread() {
    override fun run() {
        // 백그라운드 작업 수행
        println("Thread is running...")
    }
}
```
```kotlin
@Test
fun `Thread 실행`() {
    val thread = MyThread()
    thread.start()
    thread.join() // 메인 스레드가 새 스레드의 종료를 기다림(테스트 확인용)
}
```
```kotlin
@Test
fun `Runnable 실행`() {
    val runnable = Runnable {
        println("Runnable is running...")
    }

    val thread = Thread(runnable)
    thread.start()
    thread.join() // 메인 스레드가 새 스레드의 종료를 기다림(테스트 확인용)
}
```
<br/>

### ExecutorService(스레드 풀)
스레드 관리를 효율적으로 수행할 수 있는 방법으로, 스레드 풀(Thread Pool)을 활용하여 백그라운드 작업을 처리한다.<br/>

```kotlin
@Test
fun `ExecutorService 실행`() {
    val executor = Executors.newFixedThreadPool(2)
    executor.execute {
        println("Task 1 is running...")
    }
    executor.execute {
        println("Task 2 is running...")
    }
    executor.shutdown()
    executor.awaitTermination(1, TimeUnit.SECONDS) // 모든 작업이 끝날 때까지 최대 1초 대기(테스트 확인용)
}
```
<br/>

### Coroutine(코루틴)
코틀린에서 지원하는 비동기 처리 방식으로, 가벼운 스레드 관리와 강력한 동시성 제어 기능을 제공한다.<br/>

```kotlin
@Test
fun `Coroutine 실행`() {
    CoroutineScope(Dispatchers.IO).launch {
        // 백그라운드에서 실행
        println("Coroutine on IO thread")
    }
    Thread.sleep(1000) // 코루틴이 실행될 시간을 줌
}
```
<br/>
<br/>
<br/>



# Thread Safety(스레드 안전성)
- - -
스레드 안전성(Thread Safety)은 여러 스레드가 동시에 동일한 자원에 접근할 때, 데이터의 일관성을 유지하면서 오류 없이 동작하도록 보장하는 개념이다.<br/>
안드로이드에서 여러 스레드가 동시에 동일한 변수에 접근하면 경쟁 조건(Race Condition) 이 발생할 수 있으며, 이는 예측할 수 없는 결과를 초래할 수 있다.<br/>
이를 방지하기 위해 여러 가지 동기화 기법이 사용된다.<br/>
<br/>
<br/>

## synchronized
synchronized 키워드는 특정 코드 블록 또는 메서드가 한 번에 하나의 스레드에서만 실행되도록 제한하는 방법이다.<br/>
하지만, 불필요하게 남용하면 성능 저하와 데드락(Deadlock) 이 발생할 수 있다.<br/>

```kotlin
class SynchronizedCounter {
    private var count = 0

    @Synchronized
    fun increment() {
        count++
    }

    fun getCount(): Int {
        return count
    }
}
```
```kotlin
@Test
fun `Synchronized 확인`() = runBlocking {
    val counter = SynchronizedCounter()
    val jobs = List(1000) {
        launch(Dispatchers.Default) { counter.increment() }
    }
    jobs.forEach { it.join() }

    println("Count: ${counter.getCount()}")
    assertEquals(1000, counter.getCount()) // 1000이 정확히 나와야 성공
}
```
<br/>
<br/>

## volatile
`volatile` 키워드는 변수의 가시성(Visibility) 문제를 해결하며, 스레드 간 변수 값 동기화를 보장한다.<br/>
하지만 원자적(Atomic) 연산을 보장하지 않기 때문에 경쟁 상태가 발생할 수 있어 단순한 플래그 변수에 적합하다.<br/>
원자적 연산은 연산이 실행되는 동안 다른 스레드의 개입 없이 단일 단계로 실행되는 연산을 의미한다.<br/>

```kotlin
// volatile 사용 올바른 예시
class VolatileRunning {
    @Volatile
    private var isRunning = false

    fun startProcess() {
        isRunning = true
    }

    fun stopProcess() {
        isRunning = false
    }
}
```
```kotlin
@Test
fun `Volatile 변수를 사용해 count++ 연산을 실행하는 경우`() = runBlocking {
    val counter = object {
        @Volatile
        private var count = 0

        fun increment() {
            count++
        }

        fun getCount(): Int {
            return count
        }
    }
    val jobs = List(1000) {
        launch(Dispatchers.Default) { counter.increment() }
    }
    jobs.forEach { it.join() }

    // Volatile 은 원자적 연산이 아니므로 1000보다 작을 가능성이 있음
    // count == 1000이면 우연히 동기화가 된 것일 수도 있음
    // 해결 방법: volatile 은 AtomicInteger 또는 synchronized 로 대체해야 함
    println("Count: ${counter.getCount()}")    
    assertTrue(counter.getCount() < 1000)
}
```
<br/>
<br/>

## Atomic 클래스
`AtomicInteger`, `AtomicBoolean` 등의 클래스를 사용하면 복잡한 동기화 없이도 스레드 안전한 연산을 수행할 수 있다.<br/>

```kotlin
class AtomicCounter {
    private val count = AtomicInteger(0)

    fun increment() {
        count.incrementAndGet()
    }

    fun getCount(): Int {
        return count.get()
    }
}
```
```kotlin
@Test
fun `Atomic 확인`() = runBlocking {
    val counter = AtomicCounter() 
    val jobs = List(1000) {
        launch(Dispatchers.Default) { counter.increment() }
    }
    jobs.forEach { it.join() }

    println("Count: ${counter.getCount()}")    
    assertEquals(1000, counter.getCount()) // 1000이 정확히 나와야 성공
}
```
<br/>
<br/>

## ReentrantLock
`ReentrantLock` 은 `synchronized` 보다 유연한 락 메커니즘을 제공하며, 락을 명시적으로 해제할 수 있다.<br/>

```kotlin
class ReentrantLockCounter {
    private var count = 0
    private val lock = ReentrantLock()

    fun increment() {
        lock.lock()
        try {
            count++
        } finally {
            lock.unlock()
        }
    }

    fun getCount(): Int {
        lock.lock()
        try {
            return count
        } finally {
            lock.unlock()
        }
    }
}
```
```kotlin
@Test
fun `ReentrantLock 확인`() = runBlocking {
    val counter = ReentrantLockCounter() 
    val jobs = List(1000) {
        launch(Dispatchers.Default) { counter.increment() }
    }
    jobs.forEach { it.join() }

    println("Count: ${counter.getCount()}")    
    assertEquals(1000, counter.getCount()) // 1000이 정확히 나와야 성공
}
```
<br/>
<br/>

## Coroutine 을 활용한 동기화
안드로이드에서는 코루틴을 사용하여 동시성을 제어할 수 있으며, `Mutex` 또는 `Channel` 을 사용하여 스레드 안전성을 보장할 수 있다.<br/>
<br/>

### Mutax
`Mutex` 는 코루틴 환경에서 `synchronized` 와 유사한 역할을 한다.<br/>

```kotlin
class MutexCounter {
    private var count = 0
    private val mutex = Mutex()

    suspend fun increment() {
        mutex.withLock {
            count++
        }
    }

    suspend fun getCount(): Int {
        return mutex.withLock { count }
    }
}
```
```kotlin
@Test
fun `Coroutine Mutex 확인`() = runBlocking {
    val counter = MutexCounter() 
    val jobs = List(1000) {
        launch { counter.increment() }
    }
    jobs.forEach { it.join() }

    println("Count: ${counter.getCount()}")
    assertEquals(1000, counter.getCount()) // 1000이 정확히 나와야 성공
}
```
<br/>

### Channel
`Channel` 을 사용하여 안전하게 데이터 스트림을 관리할 수도 있다.<br/>

```kotlin
@Test
fun `Coroutine Channel 확인`() = runBlocking {
    val channel = Channel<Int>() 
    val job = launch {
        for (x in 1..5) {
            channel.send(x)
        }
        channel.close()
    }

    for (received in channel) {
        println("Received: $received")
    }
    job.join()
}
```