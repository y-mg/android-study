package com.ymg.architecture.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.ymg.architecture.convention.AppInfo
import com.ymg.architecture.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("kotlin-parcelize")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<ApplicationExtension> {
            compileSdk = AppInfo.COMPILE_SDK

            buildFeatures {
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }

            defaultConfig {
                applicationId = AppInfo.APPLICATION_ID

                minSdk = AppInfo.MIN_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }

                flavorDimensions += AppInfo.FLAVOR_DIMENSION
                productFlavors {
                    create(AppInfo.FLAVOR_NAME_DEV) {
                        dimension = AppInfo.FLAVOR_DIMENSION
                        manifestPlaceholders["appLabel"] = AppInfo.APP_LABEL_DEV
                        targetSdk = AppInfo.TARGET_SDK
                        versionCode = AppInfo.getVersionProperty(AppInfo.KEY_VERSION_CODE_DEV).toString().toInt()
                        versionName = AppInfo.getVersionProperty(AppInfo.KEY_VERSION_NAME).toString()
                        applicationIdSuffix = AppInfo.APPLICATION_ID_SUFFIX_DEV
                    }

                    create(AppInfo.FLAVOR_NAME_PROD) {
                        dimension = AppInfo.FLAVOR_DIMENSION
                        manifestPlaceholders["appLabel"] = AppInfo.APP_LABEL_PROD
                        targetSdk = AppInfo.TARGET_SDK
                        versionCode = AppInfo.getVersionProperty(AppInfo.KEY_VERSION_CODE_PROD).toString().toInt()
                        versionName = AppInfo.getVersionProperty(AppInfo.KEY_VERSION_NAME).toString()
                        applicationIdSuffix = AppInfo.APPLICATION_ID_SUFFIX_PROD
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

        dependencies {
            "implementation"(libs.findLibrary("timber").get())
        }
    }
}
