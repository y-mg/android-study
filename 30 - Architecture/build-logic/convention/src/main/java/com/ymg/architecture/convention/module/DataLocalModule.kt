package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DataLocalModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.coroutine.android")
            apply("convention.plugin.hilt")
            apply("convention.plugin.room")
        }

        dependencies {
            "implementation"(project(":data:core"))
            "implementation"(project(":di:qualifier"))
            "implementation"(project(":util:kotlin"))

            "implementation"(libs.findLibrary("androidx-datastore").get())
            "testImplementation"(libs.findLibrary("androidx-test-ext-junit").get())
            "testImplementation"(libs.findLibrary("junit4").get())
            "testImplementation"(libs.findLibrary("mockk").get())
            "testImplementation"(libs.findLibrary("robolectric").get())
        }
    }
}
