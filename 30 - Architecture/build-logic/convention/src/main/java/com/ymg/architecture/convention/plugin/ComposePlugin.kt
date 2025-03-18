package com.ymg.architecture.convention.plugin

import com.ymg.architecture.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<ComposeCompilerGradlePluginExtension>() {
            includeSourceInformation.set(true)
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                freeCompilerArgs.addAll(
                    listOf(
                        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                        "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
                    )
                )
            }
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-activity-compose").get())
            "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
            "androidTestImplementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
            "implementation"(libs.findLibrary("androidx-compose-foundation").get())
            "implementation"(libs.findLibrary("androidx-compose-material-icons-extended").get())
            "implementation"(libs.findLibrary("androidx-compose-material3").get())
            "implementation"(libs.findLibrary("androidx-compose-ui").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-graphics").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())
            "androidTestImplementation"(libs.findLibrary("androidx-compose-ui-test-junit4").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            "implementation"(libs.findLibrary("androidx-constraintlayout-compose").get())
            "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-compose").get())
            "implementation"(libs.findLibrary("androidx-navigation-compose").get())
            "implementation"(libs.findLibrary("androidx-paging-compose").get())
            "androidTestImplementation"(libs.findLibrary("androidx-test-espresso-core").get())
            "androidTestImplementation"(libs.findLibrary("androidx-test-ext-junit").get())
            "implementation"(libs.findLibrary("coil-compose").get())
        }
    }
}
