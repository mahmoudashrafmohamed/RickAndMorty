import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.Test
import Dependencies.TestConfigurations
import Dependencies.DaggerHilt
import Dependencies.Retrofit
import Dependencies.Skeleton
import Dependencies.Glide
import Dependencies.Chucker
import Dependencies.Logging
import java.io.FileInputStream
import java.util.Properties

val getProps by extra {
    fun(propName: String): String {
        val propsFile = rootProject.file("local.properties")
        return if (propsFile.exists()) {
            val props = Properties()
            props.load(FileInputStream(propsFile))
            (props[propName] as String?) ?: ""
        } else {
            ""
        }
    }
}

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
            buildConfigField("String", "VERSION_NAME", "\"" + Config.versionName + "\"")
            isMinifyEnabled = false
            consumerProguardFiles("proguard-rules.pro")
        }
        release {
            buildConfigField("String", "VERSION_NAME", "\"" + Config.versionName + "\"")
            isMinifyEnabled = true
            consumerProguardFiles("proguard-rules.pro")
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
    api(project(Dependencies.Modules.nativelib))

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
    api(AndroidX.recyclerView)

    // Coroutines
    api(Kotlin.Coroutines)
    // skeleton
    api(Skeleton.Androidveil)

    // Glide
    api(Glide.glide)
    kapt(Glide.glideKapt)

    // Dagger-Hilt
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltKapt)

    // Debug logging interceptor
    implementation(Retrofit.debugLoggingInterceptor) {
        exclude(group = "org.json", module = "json")
    }

    // chuker
    debugImplementation(Chucker.chuckerDebug)
    releaseImplementation(Chucker.chuckerRelease)
    api(project(Dependencies.Modules.resources))

    // Retrofit
    api(Retrofit.retrofit)
    api(Retrofit.gson)
    api(Retrofit.loggingInterceptor)

    // timber
    api(Logging.timber)

    // Test Dependencies
    testImplementation(Test.Junit)
    androidTestImplementation(Test.JunitExt)
    androidTestImplementation(Test.EspressoCore)
    testImplementation(Test.coroutines)
    testImplementation(Test.mockito)
    androidTestImplementation(Test.mockitoAndroid)
    testImplementation(Test.mockitoInline)
    testImplementation(Test.mockitoKotlinHelpers)
}