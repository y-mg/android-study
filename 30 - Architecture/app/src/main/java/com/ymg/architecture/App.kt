package com.ymg.architecture

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(
        context = this
    ).memoryCache {
        MemoryCache.Builder(
            context = this
        ).maxSizePercent(
            percent = 0.25
        ).build()
    }.logger(
        logger = DebugLogger()
    ).build()
}
