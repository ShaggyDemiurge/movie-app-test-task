import buildsrc.Lib

plugins {
    id("com.android.library")
    id("android-module")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Compose.CompilerVersion
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
}
