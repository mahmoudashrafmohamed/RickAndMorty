import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.Test
import Dependencies.TestConfigurations

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
}

configurations {
    create(TestConfigurations.testImplementation){
        extendsFrom(configurations.testImplementation.get())
    }
    create(TestConfigurations.androidTestImplementation){
        extendsFrom(configurations.androidTestImplementation.get())
    }
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Core Dependencies
    api(Kotlin.Stdlib)
    api(AndroidX.CoreKtx)
    api(AndroidX.AppCompat)
    api(AndroidX.Material)
    api(AndroidX.ConstraintLayout)

    // Coroutines
    api(Kotlin.Coroutines)

    // Test Dependencies
    testImplementation(Test.Junit)
    androidTestImplementation(Test.JunitExt)
    androidTestImplementation(Test.EspressoCore)
}