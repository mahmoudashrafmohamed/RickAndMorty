import Dependencies.Modules
import Dependencies.TestConfigurations
import Dependencies.DaggerHilt
import Dependencies.TestConfigurations.androidTestImplementation
import Dependencies.TestConfigurations.testImplementation

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
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
    implementation(project(Modules.local))
    implementation(project(Modules.core)) {
        testImplementation(project(path = Modules.core, configuration = TestConfigurations.testImplementation))
        androidTestImplementation(project(path = Modules.core, configuration = TestConfigurations.androidTestImplementation))
        testImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6")
        testImplementation( "org.mockito:mockito-core:3.2.4")
        androidTestImplementation ("org.mockito:mockito-android:3.2.4")
        testImplementation ("org.mockito:mockito-inline:2.28.2")
        testImplementation( "com.nhaarman:mockito-kotlin:1.6.0")
    }
    // Dagger-Hilt
    implementation (DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltKapt)

    implementation ("androidx.recyclerview:recyclerview:1.2.1")

}