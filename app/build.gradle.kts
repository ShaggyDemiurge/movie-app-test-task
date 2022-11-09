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

    buildTypes {
        getByName("release") {
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
