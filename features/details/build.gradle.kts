import Dependencies.Modules
import Dependencies.DaggerHilt
import Dependencies.TestConfigurations.androidTestImplementation
import Dependencies.TestConfigurations.testImplementation

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core)) {
        testImplementation(project(path = Modules.core, configuration = testImplementation))
        androidTestImplementation(project(path = Modules.core, configuration = androidTestImplementation))
    }
    // Dagger-Hilt
    implementation (DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltKapt)
}