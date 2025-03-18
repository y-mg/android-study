package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UtilAndroidModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.coroutine.android")
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-activity").get())
        }
    }
}
