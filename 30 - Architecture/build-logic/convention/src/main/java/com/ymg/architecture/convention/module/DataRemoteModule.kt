package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DataRemoteModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.coroutine.android")
            apply("convention.plugin.hilt")
            apply("convention.plugin.kotlin.serialization")
            apply("convention.plugin.retrofit")
        }

        dependencies {
            "implementation"(project(":data:core"))
            "implementation"(project(":di:qualifier"))
            "implementation"(project(":util:kotlin"))

            "implementation"(libs.findLibrary("androidx-paging-runtime").get())
            "testImplementation"(libs.findLibrary("junit4").get())
            "implementation"(libs.findLibrary("okhttp-logging").get())
        }
    }
}
