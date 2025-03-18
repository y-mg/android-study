package com.ymg.thread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ExampleUnitTest {
    @Test
    fun `Thread 실행`() {
        val thread = MyThread()
        thread.start()
        thread.join() // 메인 스레드가 새 스레드의 종료를 기다림(테스트 확인용)
    }

    @Test
    fun `Runnable 실행`() {
        val runnable = Runnable {
            println("Runnable is running...")
        }

        val thread = Thread(runnable)
        thread.start()
        thread.join() // 메인 스레드가 새 스레드의 종료를 기다림(테스트 확인용)
    }

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

    @Test
    fun `Coroutine 실행`() {
        CoroutineScope(Dispatchers.IO).launch {
            // 백그라운드에서 실행
            println("Coroutine on IO thread")
        }
        Thread.sleep(1000) // 코루틴이 실행될 시간을 줌
    }

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
}