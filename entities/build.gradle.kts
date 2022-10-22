plugins {
    id(Plugins.androidLib)
    id(Plugins.kotlinAndroid)
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
    implementation(Dependencies.Retrofit.gson)
}