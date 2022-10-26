import Dependencies.Modules
import Dependencies.TestConfigurations
import Dependencies.DaggerHilt

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
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
}

dependencies {
    implementation(project(Modules.core)) {
        testImplementation(project(path = Modules.core, configuration = TestConfigurations.testImplementation))
        androidTestImplementation(project(path = Modules.core, configuration = TestConfigurations.androidTestImplementation))
    }
    implementation(project(Modules.local))

    // Dagger-Hilt
    implementation (DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltKapt)
}