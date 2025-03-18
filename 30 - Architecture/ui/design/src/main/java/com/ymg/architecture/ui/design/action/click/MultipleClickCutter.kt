package com.ymg.architecture.ui.design.action.click

interface MultipleClickCutter {
    companion object

    fun processEvent(
        event: () -> Unit
    )
}

private class MultipleClickCutterImpl : MultipleClickCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMillis: Long = 0

    override fun processEvent(
        event: () -> Unit
    ) {
        if (now - lastEventTimeMillis >= 500L) {
            event.invoke()
        }
        lastEventTimeMillis = now
    }
}

fun MultipleClickCutter.Companion.get(): MultipleClickCutter = MultipleClickCutterImpl()
