import buildsrc.Config

plugins {
    id("com.android.application")
    id("android-module")
}

android {
    defaultConfig {
        applicationId = Config.appId

        buildConfigField("String", "API_BASE_URL", "\"${Config.apiUrl}\"")
        buildConfigField("String", "API_KEY", "\"${Config.apiKey}\"")
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file(property("DEBUG_STORE_FILE") as String)
            storePassword = property("DEBUG_STORE_PASSWORD") as String
            keyAlias = property("DEBUG_KEY_ALIAS") as String
            keyPassword = property("DEBUG_KEY_PASSWORD") as String
        }

        create("release") {
            // TODO replace with release keys for real project
            storeFile = file(property("DEBUG_STORE_FILE") as String)
            storePassword = property("DEBUG_STORE_PASSWORD") as String
            keyAlias = property("DEBUG_KEY_ALIAS") as String
            keyPassword = property("DEBUG_KEY_PASSWORD") as String
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}
