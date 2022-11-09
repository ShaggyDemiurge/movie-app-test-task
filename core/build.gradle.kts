import buildsrc.Lib

plugins {
    id("com.android.library")
    id("android-module")
    id("android-desugar")
}

dependencies {
    api(Lib.Koin)
    api(Lib.Logger.slf4j)
    runtimeOnly(Lib.Logger.logbackAndroid)
}
