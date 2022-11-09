buildscript {
    // Plugin versions are added in buildsrc to allow configuration from there
}

plugins {
    id("com.google.devtools.ksp") version ("1.7.10-1.0.6")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
