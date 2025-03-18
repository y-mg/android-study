/**
 * Android Studio 버그
 * Rebuild 시 실제 빌드는 완료되지만, Unable to make progress running work 에러가 출력
 */
gradle.startParameter.excludedTaskNames.addAll(
    gradle.startParameter.taskNames.filter {
        it.contains("testClasses")
    }
)

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Architecture"
include(":app")
include(
    ":data:core",
    ":data:local-user",
    ":data:remote-photo"
)
include(
    ":di:core",
    ":di:qualifier"
)
include(":domain")
include(
    ":feature:photo"
)
include(":presentation")
include(
    ":ui:core",
    ":ui:design",
    ":ui:navigator",
    ":ui:resource"
)
include(
    ":util:android",
    ":util:kotlin"
)
