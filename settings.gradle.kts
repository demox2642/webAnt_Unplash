pluginManagement {
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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WebAnt_Unsplush"
include(":app")
include(":base:presentation")
include(":base:data")
include(":base:domain")
include(":home:presentation")
include(":home:data")
include(":home:domain")
include(":photo:presentation")
include(":photo:data")
include(":photo:domain")
include(":profile:presentation")
include(":profile:data")
include(":profile:domain")
