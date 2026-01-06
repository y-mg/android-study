plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.plugin.android.gradle)
    compileOnly(libs.plugin.kotlin.compose.gradle)
    compileOnly(libs.plugin.kotlin.gradle)
}

gradlePlugin {
    plugins {
        val path = "com.ymg.architecture.convention.plugin"

        register("AndroidApplicationPlugin") {
            id = "convention.plugin.android.application"
            implementationClass = "${path}.AndroidApplicationPlugin"
        }
        register("AndroidLibraryPlugin") {
            id = "convention.plugin.android.library"
            implementationClass = "${path}.AndroidLibraryPlugin"
        }
        register("ComposePlugin") {
            id = "convention.plugin.compose"
            implementationClass = "${path}.ComposePlugin"
        }
        register("CoroutineAndroidPlugin") {
            id = "convention.plugin.coroutine.android"
            implementationClass = "${path}.CoroutineAndroidPlugin"
        }
        register("CoroutineCorePlugin") {
            id = "convention.plugin.coroutine.core"
            implementationClass = "${path}.CoroutineCorePlugin"
        }
        register("HiltPlugin") {
            id = "convention.plugin.hilt"
            implementationClass = "${path}.HiltPlugin"
        }
        register("JavaLibraryPlugin") {
            id = "convention.plugin.java.library"
            implementationClass = "${path}.JavaLibraryPlugin"
        }
        register("KotlinSerializationPlugin") {
            id = "convention.plugin.kotlin.serialization"
            implementationClass = "${path}.KotlinSerializationPlugin"
        }
        register("OrbitPlugin") {
            id = "convention.plugin.orbit"
            implementationClass = "${path}.OrbitPlugin"
        }
        register("RetrofitPlugin") {
            id = "convention.plugin.retrofit"
            implementationClass = "${path}.RetrofitPlugin"
        }
        register("RoomPlugin") {
            id = "convention.plugin.room"
            implementationClass = "${path}.RoomPlugin"
        }
    }

    plugins {
        val path = "com.ymg.architecture.convention.module"

        register("AppModule") {
            id = "convention.module.app"
            implementationClass = "${path}.AppModule"
        }
        register("DataCoreModule") {
            id = "convention.module.data.core"
            implementationClass = "${path}.DataCoreModule"
        }
        register("DataLocalModule") {
            id = "convention.module.data.local"
            implementationClass = "${path}.DataLocalModule"
        }
        register("DataRemoteModule") {
            id = "convention.module.data.remote"
            implementationClass = "${path}.DataRemoteModule"
        }
        register("DiCoreModule") {
            id = "convention.module.di.core"
            implementationClass = "${path}.DiCoreModule"
        }
        register("DiQualifierModule") {
            id = "convention.module.di.qualifier"
            implementationClass = "${path}.DiQualifierModule"
        }
        register("DomainModule") {
            id = "convention.module.domain"
            implementationClass = "${path}.DomainModule"
        }
        register("FeatureModule") {
            id = "convention.module.feature"
            implementationClass = "$path.FeatureModule"
        }
        register("PresentationModule") {
            id = "convention.module.presentation"
            implementationClass = "${path}.PresentationModule"
        }
        register("UiCoreModule") {
            id = "convention.module.ui.core"
            implementationClass = "${path}.UiCoreModule"
        }
        register("UiDesignModule") {
            id = "convention.module.ui.design"
            implementationClass = "${path}.UiDesignModule"
        }
        register("UiNavigatorModule") {
            id = "convention.module.ui.navigator"
            implementationClass = "${path}.UiNavigatorModule"
        }
        register("UiResourceModule") {
            id = "convention.module.ui.resource"
            implementationClass = "${path}.UiResourceModule"
        }
        register("UtilAndroidModule") {
            id = "convention.module.util.android"
            implementationClass = "${path}.UtilAndroidModule"
        }
        register("UtilKotlinModule") {
            id = "convention.module.util.kotlin"
            implementationClass = "${path}.UtilKotlinModule"
        }
    }
}
