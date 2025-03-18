package com.ymg.memory

import org.junit.Test

class ExampleUnitTest {
    // 실행 시 java.lang.OutOfMemoryError: Java heap space
    @Test
    fun `힙 사용`() {
        val list = mutableListOf<LargeObject>()
        for (i in 0 until 100) {
            list.add(LargeObject()) // 힙 메모리를 빠르게 소비
        }
    }

    @Test
    fun `스택 사용`() {
        exampleUsedStack(100000)
    }
}