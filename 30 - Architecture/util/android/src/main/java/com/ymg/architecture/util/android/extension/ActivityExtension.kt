package com.ymg.architecture.util.android.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Context> Activity.startActivity(
    intentBuilder: Intent.() -> Intent = { this },
    isFinish: Boolean = false,
    isFinishAffinity: Boolean = false
) {
    startActivity(intentBuilder(Intent(this, T::class.java)))
    if (isFinish) {
        finish()
    }
    if (isFinishAffinity) {
        finishAffinity()
    }
}
