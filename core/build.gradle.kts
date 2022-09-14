import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.Test
import Dependencies.TestConfigurations
import Dependencies.DaggerHilt
import Dependencies.Retrofit

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
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
    api(AndroidX.NavigationUiKtx)
    api(AndroidX.ActivityKtx)

    // Coroutines
    api(Kotlin.Coroutines)

    // Dagger-Hilt
    api (DaggerHilt.hiltAndroid)
    api (DaggerHilt.hiltViewModel)
    kapt(DaggerHilt.hiltKapt)

    // Retrofit
    api(Retrofit.retrofit)
    api(Retrofit.gson)
    api(Retrofit.loggingInterceptor)

    // Test Dependencies
    testImplementation(Test.Junit)
    androidTestImplementation(Test.JunitExt)
    androidTestImplementation(Test.EspressoCore)

    api (AndroidX.LifecycleKTX)

}