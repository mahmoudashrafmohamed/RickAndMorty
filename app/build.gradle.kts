import Dependencies.Modules
import Dependencies.TestConfigurations

plugins {
    id(Plugins.android)
    id(Plugins.kotlinAndroid)
    id(Plugins.daggerHilt)
    id(Plugins.kotlinKapt)
    id(Plugins.checkDependencyUpdates) version Versions.CheckDependencyUpdates

}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    signingConfigs {
        create("release") {
            keyAlias = "rick"
            keyPassword = "123456"
            storeFile = file("C:/Users/mahmoud_ashraf/rick_and_morty.jks")
            storePassword = "123456"
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            manifestPlaceholders["appName"] = "@string/app_name_debug"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_debug"
            manifestPlaceholders["appIconRounded"] = "@mipmap/ic_launcher_debug_round"
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            manifestPlaceholders["appName"] = "@string/app_name"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            manifestPlaceholders["appIconRounded"] = "@mipmap/ic_launcher_round"
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.splash))
    implementation(project(Modules.about))
    implementation(project(Modules.details))
    implementation(project(Modules.home))
    implementation(project(Modules.core)) {
        testImplementation(
            project(
                path = Modules.core,
                configuration = TestConfigurations.testImplementation
            )
        )
        androidTestImplementation(
            project(
                path = Modules.core,
                configuration = TestConfigurations.androidTestImplementation
            )
        )
    }
    // Dagger-Hilt
    implementation(Dependencies.DaggerHilt.hiltAndroid)
    kapt(Dependencies.DaggerHilt.hiltKapt)
}