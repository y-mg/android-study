package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DataCoreModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.coroutine.android")
            apply("convention.plugin.hilt")
            apply("convention.plugin.kotlin.serialization")
            apply("convention.plugin.retrofit")
            apply("convention.plugin.room")
        }

        dependencies {
            "implementation"(project(":di:qualifier"))
            "implementation"(project(":domain"))
            "implementation"(project(":util:kotlin"))

            "implementation"(libs.findLibrary("androidx-paging-runtime").get())
            "testImplementation"(libs.findLibrary("androidx-test-ext-junit").get())
            "implementation"(libs.findLibrary("okhttp-logging").get())
        }
    }
}
