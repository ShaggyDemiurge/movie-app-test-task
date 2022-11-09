package buildsrc

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DesugaringPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.dependencies {
            add("coreLibraryDesugaring", Lib.Android.Desugaring)
        }
        target.extensions.configure<BaseExtension> {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}
