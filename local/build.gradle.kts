
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
    implementation(project(Dependencies.Modules.core))
    api(Dependencies.Room.roomKtx)
    api(Dependencies.Room.roomRuntime)
    implementation("androidx.test.ext:junit-ktx:1.1.3")
    kapt(Dependencies.Room.roomCompilerKapt)
    // DataStore
    api(Dependencies.DataStore.prefDataStore)
    // Dagger-Hilt
    implementation (Dependencies.DaggerHilt.hiltAndroid)
    kapt(Dependencies.DaggerHilt.hiltKapt)
}