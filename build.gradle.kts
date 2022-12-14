// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven ( url = "https://jitpack.io" )
    }
    dependencies {
        classpath(GradleConfig.Android)
        classpath(GradleConfig.Kotlin)
        classpath(GradleConfig.hilt)
        classpath(GradleConfig.spotlessGradlePlugin)
        classpath ("com.google.gms:google-services:4.3.13")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }

}

subprojects {
    apply(from = "$rootDir/spotless/spotless.gradle")
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven ( url ="https://jitpack.io" )

    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}