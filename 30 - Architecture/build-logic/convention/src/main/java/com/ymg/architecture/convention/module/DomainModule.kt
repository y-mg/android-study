package com.ymg.architecture.convention.module

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DomainModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.java.library")
            apply("convention.plugin.coroutine.core")
        }

        dependencies {
            "implementation"(project(":util:kotlin"))

            "implementation"(libs.findLibrary("androidx-paging-common").get())
            "testImplementation"(libs.findLibrary("mockk").get())
        }
    }
}
