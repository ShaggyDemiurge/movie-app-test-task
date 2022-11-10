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
        const val Desugaring = "com.android.tools:desugar_jdk_libs:1.1.5"
    }

    object Compose {
        const val Version = "1.3.0"
        const val Core = "androidx.compose.ui:ui:$Version"
        const val UiTooling = "androidx.compose.ui:ui-tooling:$Version"
        const val Preview = "androidx.compose.ui:ui-tooling-preview:$Version"
        const val Material = "androidx.compose.material:material:$Version"
        const val MaterialIconsExt = "androidx.compose.material:material-icons-extended:$Version"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        // Using this instead of standard navigation for argument support and type safety without adding KSP
        const val Navigation = "dev.olshevski.navigation:reimagined:1.3.0"
    }

    object Logger {
        const val slf4j = "org.slf4j:slf4j-api:1.7.33"
        const val logbackAndroid = "com.github.tony19:logback-android:2.0.0"
    }

    const val Koin = "io.insert-koin:koin-androidx-compose:3.3.0"
    const val Landscapist = "com.github.skydoves:landscapist-glide:2.0.3"

    object Network {
        private const val okHttp3Version = "4.10.0"
        const val okHttp3 = "com.squareup.okhttp3:okhttp:$okHttp3Version"
        const val okHttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttp3Version"
        private const val retrofitVersion = "2.9.0"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofit2moshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    }

    object Moshi {
        private const val version = "1.14.0"
        const val core = "com.squareup.moshi:moshi:$version"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }
}
