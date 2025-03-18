package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UiResourceModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-core-splashscreen").get())
        }
    }
}
