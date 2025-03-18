package com.ymg.memory

class LargeObject {
    private val data = ByteArray(10 * 1024 * 1024) // 10MB 크기의 배열
}

// count 파라미터에 100000 를 넣고 실행 시 java.lang.StackOverflowError
fun exampleUsedStack(count: Int) {
    if (count == 0) {
        return
    }
    exampleUsedStack(count - 1) // 깊은 재귀 호출
}