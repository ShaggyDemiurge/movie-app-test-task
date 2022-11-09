import buildsrc.Lib

plugins {
    id("com.android.library")
    id("android-module")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core"))

    implementation(Lib.Moshi.core)
    ksp(Lib.Moshi.codegen)
}
