package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DiCoreModule : Plugin<Project> {
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
            "implementation"(project(":data:core"))
            "implementation"(project(":data:local-user"))
            "implementation"(project(":data:remote-photo"))
            "implementation"(project(":di:qualifier"))
            "implementation"(project(":domain"))

            "implementation"(libs.findLibrary("androidx-datastore").get())
            "implementation"(libs.findLibrary("okhttp-logging").get())
        }
    }
}
