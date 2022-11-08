package buildsrc

@Suppress("MemberVisibilityCanBePrivate")
object Lib {

    object Kotlin {
        const val Version = "1.7.10"
        const val StdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$Version"
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
        const val Navigation = "androidx.navigation:navigation-compose:2.5.3"
    }
}
