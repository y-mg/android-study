package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("dagger.hilt.android.plugin")
            apply("com.google.devtools.ksp")
        }

        dependencies {
            "implementation"(libs.findLibrary("dagger-hilt-android").get())
            "ksp"(libs.findLibrary("dagger-hilt-compiler").get())
            "kspTest"(libs.findLibrary("dagger-hilt-compiler").get())
            "testImplementation"(libs.findLibrary("dagger-hilt-testing").get())
        }
    }
}
