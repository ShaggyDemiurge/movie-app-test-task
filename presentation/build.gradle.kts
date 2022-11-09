import buildsrc.Lib

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("android-module")
    id("android-desugar")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Compose.Version
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

    implementation(Lib.Android.Core)
    implementation(Lib.Android.AppCompat)
    implementation(Lib.Compose.Core)
    debugImplementation(Lib.Compose.UiTooling)
    implementation(Lib.Compose.Preview)
    implementation(Lib.Compose.Material)
    implementation(Lib.Compose.ConstraintLayout)
    implementation(Lib.Compose.Navigation)
    implementation(Lib.Landscapist)
}
