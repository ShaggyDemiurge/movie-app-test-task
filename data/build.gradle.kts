import buildsrc.Lib

plugins {
    id("com.android.library")
    id("android-module")
}

dependencies {
    implementation(project(":core"))

    implementation(Lib.Network.okHttp3)
    implementation(Lib.Network.okHttp3LoggingInterceptor)
    implementation(Lib.Network.retrofit2)
    implementation(Lib.Network.retrofit2moshi)
    implementation(Lib.Moshi.core)
}
