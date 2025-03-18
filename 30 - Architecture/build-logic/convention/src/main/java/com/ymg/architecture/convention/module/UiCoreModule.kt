package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class UiCoreModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.compose")
            apply("convention.plugin.coroutine.android")
            apply("convention.plugin.hilt")
            apply("convention.plugin.orbit")
        }

        dependencies {
            "implementation"(project(":domain"))
            "implementation"(project(":ui:design"))
            "implementation"(project(":ui:resource"))
            "implementation"(project(":util:android"))
            "implementation"(project(":util:kotlin"))

            "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel").get())
        }
    }
}
