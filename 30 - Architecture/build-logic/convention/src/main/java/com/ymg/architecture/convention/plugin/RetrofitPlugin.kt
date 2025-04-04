package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        dependencies {
            "implementation"(libs.findLibrary("retrofit-core").get())
            "implementation"(libs.findLibrary("retrofit-kotlin-serialization").get())
        }
    }
}
