import buildsrc.Lib

plugins {
    id("com.android.library")
    id("android-module")
}

dependencies {
    api(Lib.Koin)
}
