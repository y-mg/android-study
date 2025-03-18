package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {
	override fun apply(target: Project) = with(target) {
		with(pluginManager) {
			apply("com.google.devtools.ksp")
		}

		dependencies {
            "implementation"(libs.findLibrary("androidx-room").get())
            "ksp"(libs.findLibrary("androidx-room-compiler").get())
            "implementation"(libs.findLibrary("androidx-room-paging").get())
            "implementation"(libs.findLibrary("androidx-room-runtime").get())
			"testImplementation"(libs.findLibrary("androidx-room-testing").get())
		}
	}
}
