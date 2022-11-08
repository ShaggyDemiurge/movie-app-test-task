import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.jmailen.gradle.kotlinter.KotlinterExtension
import java.net.URI

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.repositories {
            mavenCentral()
            google()
            maven { url = URI.create("https://plugins.gradle.org/m2/") }
        }
        target.pluginManager.apply("org.jmailen.kotlinter")
        target.extensions.configure<KotlinterExtension> {
            ignoreFailures = false
            reporters = arrayOf("checkstyle", "plain")
            experimentalRules = false
        }
        target.extensions.configure<BaseExtension> {
            compileSdkVersion(Config.compileSdk)
            defaultConfig {
                applicationId = Config.appId
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
    }
}
