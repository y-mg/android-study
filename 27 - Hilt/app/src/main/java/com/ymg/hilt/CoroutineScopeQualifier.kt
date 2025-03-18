package com.ymg.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCoroutineScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainCoroutineScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoCoroutineScope