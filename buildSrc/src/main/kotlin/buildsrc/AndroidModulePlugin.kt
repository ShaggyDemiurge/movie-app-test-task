package buildsrc

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jmailen.gradle.kotlinter.KotlinterExtension

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.android")
        target.pluginManager.apply("org.jmailen.kotlinter")
        target.extensions.configure<KotlinterExtension> {
            ignoreFailures = false
            reporters = arrayOf("checkstyle", "plain")
            experimentalRules = false
        }
        target.extensions.configure<BaseExtension> {
            compileSdkVersion(Config.compileSdk)
            defaultConfig {
                minSdk = Config.minSdk
                targetSdk = Config.targetSdk
                versionCode = Config.versionCode
                versionName = Config.versionName
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
        target.dependencies {
            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Lib.Kotlin.StdLib)
            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Lib.Kotlin.Coroutines)
        }
    }
}
