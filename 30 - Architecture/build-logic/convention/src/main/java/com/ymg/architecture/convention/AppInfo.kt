package com.ymg.architecture.convention

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

object AppInfo {
	const val APPLICATION_ID = "com.ymg.architecture"

	const val APPLICATION_ID_SUFFIX_DEV = ".dev"
	const val APPLICATION_ID_SUFFIX_PROD = ""

	const val FLAVOR_DIMENSION = "versions"

	const val FLAVOR_NAME_DEV = "dev"
	const val FLAVOR_NAME_PROD = "prod"

	const val APP_LABEL_DEV = "Architecture(DEV)"
	const val APP_LABEL_PROD = "Architecture"

	const val KEY_VERSION_NAME = "VERSION_NAME"
	const val KEY_VERSION_CODE_DEV = "VERSION_CODE_DEV"
	const val KEY_VERSION_CODE_PROD = "VERSION_CODE_PROD"

	const val COMPILE_SDK = 35
	const val TARGET_SDK = 35
	const val MIN_SDK = 28

	const val BASE_URL_DEV = "https://api.unsplash.com/"
	const val BASE_URL_PROD = "https://api.unsplash.com/"

	fun getVersionProperty(key: String): Any {
		val file = "version.properties"
		val properties = Properties()
		val versionProperties = File(file)
		if (versionProperties.isFile) {
			InputStreamReader(FileInputStream(versionProperties), Charsets.UTF_8).use { reader ->
				properties.load(reader)
			}
		} else {
			error("$file from not found")
		}

		return properties.getProperty(key)
	}
}
