import Dependencies.Modules
import Dependencies.DaggerHilt
import Dependencies.TestConfigurations.androidTestImplementation
import Dependencies.TestConfigurations.testImplementation
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            consumerProguardFiles( "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            consumerProguardFiles( "proguard-rules.pro")
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
    implementation(project(Dependencies.Modules.core)) {
        testImplementation(project(path = Modules.core, configuration = testImplementation))
        androidTestImplementation(project(path = Modules.core, configuration = androidTestImplementation))
    }
}