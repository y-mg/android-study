package com.ymg.thread

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class MyThread : Thread() {
    override fun run() {
        // 백그라운드 작업 수행
        println("Thread is running...")
    }
}

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

class AtomicCounter {
    private val count = AtomicInteger(0)

    fun increment() {
        count.incrementAndGet()
    }

    fun getCount(): Int {
        return count.get()
    }
}

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