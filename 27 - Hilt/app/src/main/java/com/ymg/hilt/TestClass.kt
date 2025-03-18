package com.ymg.hilt

import javax.inject.Inject

class TestClass @Inject constructor() {
    fun test(): String = "Test"
}

class DoTestClass @Inject constructor(
    private val testClass: TestClass
) {
    fun doTest(): String = testClass.test()
}