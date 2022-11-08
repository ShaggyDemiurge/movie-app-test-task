package buildsrc

@Suppress("MemberVisibilityCanBePrivate")
object Lib {

    object Kotlin {
        const val Version = "1.7.10"
        const val StdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$Version"
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Android {
        const val Core = "androidx.core:core-ktx:1.9.0"
        const val AppCompat = "androidx.appcompat:appcompat:1.5.1"
        const val ActivityExt = "androidx.activity:activity-ktx:1.6.1"
    }

    object Compose {
        const val Version = "1.3.0"
        const val Core = "androidx.compose.ui:ui:$Version"
        const val UiTooling = "androidx.compose.ui:ui-tooling:$Version"
        const val Preview = "androidx.compose.ui:ui-tooling-preview:$Version"
        const val Foundation = "androidx.compose.foundation:foundation:$Version"
        const val Material = "androidx.compose.material:material:$Version"
        const val MaterialIconsCore = "androidx.compose.material:material-icons-core:$Version"
        const val MaterialIconsExt = "androidx.compose.material:material-icons-extended:$Version"

        // Using this instead of standard navigation for argument support and type safety without adding KSP
        const val Navigation = "dev.olshevski.navigation:reimagined:1.3.0"
    }

    object Logger {
        const val slf4j = "org.slf4j:slf4j-api:1.7.33"
        const val logbackAndroid = "com.github.tony19:logback-android:2.0.0"
    }

    const val Koin = "io.insert-koin:koin-androidx-compose:3.3.0"
}
