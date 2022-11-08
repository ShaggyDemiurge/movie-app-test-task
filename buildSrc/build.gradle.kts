
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}
dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")

    @Suppress("GradleDependency") // 3.12.0 is bugged when trying to gradle sync
    implementation("org.jmailen.gradle:kotlinter-gradle:3.10.0")
}

gradlePlugin {
    plugins {
        create("android-module") {
            id = "android-module"
            implementationClass = "buildsrc.AndroidModulePlugin"
        }
    }
}
