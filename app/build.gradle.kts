plugins {
    id("com.android.application")
    id("android-module")
}

android {
    namespace = buildsrc.Config.appId

    defaultConfig {
        applicationId = buildsrc.Config.appId
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
    implementation(project(":presentation"))
}
