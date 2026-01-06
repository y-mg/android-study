package com.ymg.architecture.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.ymg.architecture.convention.AppInfo
import com.ymg.architecture.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("kotlin-parcelize")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            compileSdk = AppInfo.COMPILE_SDK

            buildFeatures {
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }

            defaultConfig {
                minSdk = AppInfo.MIN_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }

                flavorDimensions += AppInfo.FLAVOR_DIMENSION
                productFlavors {
                    create(AppInfo.FLAVOR_NAME_DEV) {
                        dimension = AppInfo.FLAVOR_DIMENSION
                        buildConfigField("String", "APP_LABEL", "\"${AppInfo.APP_LABEL_DEV}\"")
                        buildConfigField("String", "BASE_URL", "\"${AppInfo.BASE_URL_DEV}\"")
                    }

                    create(AppInfo.FLAVOR_NAME_PROD) {
                        dimension = AppInfo.FLAVOR_DIMENSION
                        buildConfigField("String", "APP_LABEL", "\"${AppInfo.APP_LABEL_PROD}\"")
                        buildConfigField("String", "BASE_URL", "\"${AppInfo.BASE_URL_PROD}\"")
                    }
                }
            }

            @Suppress("UnstableApiUsage")
            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
                freeCompilerArgs.addAll(
                    listOf(
                        "-opt-in=kotlinx.coroutines.DelicateCoroutinesApi",
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-opt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
                        "-opt-in=kotlinx.coroutines.FlowPreview"
                    )
                )
            }
        }

        dependencies {
            "implementation"(libs.findLibrary("timber").get())
        }
    }
}
