package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CoroutineCorePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        dependencies {
            "implementation"(libs.findLibrary("kotlinx-coroutines-core").get())
            "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
            "testImplementation"(libs.findLibrary("turbine").get())
        }
    }
}
