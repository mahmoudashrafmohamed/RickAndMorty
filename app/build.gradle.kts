import Dependencies.Modules
import Dependencies.TestConfigurations

plugins {
    id(Plugins.android)
    id(Plugins.kotlinAndroid)
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
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
    implementation(project(Modules.home))
    implementation(project(Modules.core)) {
        testImplementation(project(path = Modules.core, configuration = TestConfigurations.testImplementation))
        androidTestImplementation(project(path = Modules.core, configuration = TestConfigurations.androidTestImplementation))
    }
}