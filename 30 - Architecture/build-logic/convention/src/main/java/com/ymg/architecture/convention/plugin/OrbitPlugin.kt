package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class OrbitPlugin : Plugin<Project> {
	override fun apply(target: Project) = with(target) {
		dependencies {
            "implementation"(libs.findLibrary("orbit-core").get())
            "implementation"(libs.findLibrary("orbit-compose").get())
            "implementation"(libs.findLibrary("orbit-viewmodel").get())
            "testImplementation"(libs.findLibrary("orbit-test").get())
		}
	}
}
