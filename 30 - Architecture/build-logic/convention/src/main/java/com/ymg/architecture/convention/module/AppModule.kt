package com.ymg.architecture.convention.module

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AppModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.application")
            apply("convention.plugin.compose")
            apply("convention.plugin.coroutine.android")
            apply("convention.plugin.hilt")
        }

        dependencies {
            "implementation"(project(":di:core"))
            "implementation"(project(":presentation"))
            "implementation"(project(":ui:resource"))
        }
    }
}
