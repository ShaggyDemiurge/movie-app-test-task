buildscript {
    // Plugin versions are added in buildsrc to allow configuration from there
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
