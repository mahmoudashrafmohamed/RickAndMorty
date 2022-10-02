import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.Test
import Dependencies.TestConfigurations
import Dependencies.DaggerHilt
import Dependencies.Retrofit
import Dependencies.Skeleton
import Dependencies.DataStore
import Dependencies.Glide
import Dependencies.Chucker

plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
}

configurations {
    create(TestConfigurations.testImplementation) {
        extendsFrom(configurations.testImplementation.get())
    }
    create(TestConfigurations.androidTestImplementation) {
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
        debug {
            buildConfigField("String", "BASE_URL", "\"" + LocalProperties.baseUrl + "\"")

        }
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

    api(project(Dependencies.Modules.entities))
    // Core Dependencies
    api(Kotlin.Stdlib)
    api(AndroidX.CoreKtx)
    api(AndroidX.AppCompat)
    api(AndroidX.Material)
    api(AndroidX.ConstraintLayout)
    api(AndroidX.NavigationUiKtx)
    api(AndroidX.ActivityKtx)
    api(AndroidX.NavigationFragment)
    api(AndroidX.LifecycleViewModel)
    api(AndroidX.FragmentKtx)
    api(AndroidX.LifecycleKTX)
    api (AndroidX.recyclerView)

    // Coroutines
    api(Kotlin.Coroutines)
    // skeleton
    api(Skeleton.Androidveil)
    // Retrofit
    api(Retrofit.retrofit)
    api(Retrofit.gson)
    api(Retrofit.loggingInterceptor)
    // Glide
    api(Glide.glide)
    kapt(Glide.glideKapt)

    // Dagger-Hilt
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltKapt)

    // DataStore
    api(DataStore.prefDataStore)

    // Debug logging interceptor
    implementation(Retrofit.debugLoggingInterceptor) {
        exclude(group = "org.json", module = "json")
    }

    // chuker
    debugImplementation(Chucker.chuckerDebug)
    releaseImplementation(Chucker.chuckerRelease)

    api(project(Dependencies.Modules.resources))


    // Test Dependencies
    testImplementation(Test.Junit)
    androidTestImplementation(Test.JunitExt)
    androidTestImplementation(Test.EspressoCore)
    testImplementation( Test.coroutines)
    testImplementation( Test.mockito)
    androidTestImplementation (Test.mockitoAndroid)
    testImplementation (Test.mockitoInline)
    testImplementation( Test.mockitoKotlinHelpers)
}