package com.ymg.application

import android.content.Context
import android.location.LocationManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun `애플리케이션_리소스_접근`() {
        val appName = context.getString(R.string.app_name)

        println("AppName: $appName")
        assertEquals("Application", appName)
    }

    @Test
    fun `시스템_서비스_접근`() {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Test
    fun `애플리케이션_환경_정보_접근`() {
        val packageName = context.packageName

        println("AppName: $packageName")
        assertEquals("com.ymg.application", packageName)
    }
}