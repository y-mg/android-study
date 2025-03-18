package com.ymg.architecture.convention.module

import org.gradle.api.Plugin
import org.gradle.api.Project

class DiQualifierModule : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("convention.plugin.java.library")
        }
    }
}
