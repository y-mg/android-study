package com.ymg.architecture.convention.module

import org.gradle.api.Plugin
import org.gradle.api.Project

class UiNavigatorModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.android.library")
            apply("convention.plugin.compose")
        }
    }
}
