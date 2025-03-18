package com.ymg.architecture.convention.module

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class UiDesignModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.compose")
        }

        dependencies {
            "implementation"(project(":ui:resource"))
            "implementation"(project(":util:android"))
            "implementation"(project(":util:kotlin"))
        }
    }
}
