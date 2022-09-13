object Dependencies {

    object Kotlin {
        const val Stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.coreKTX}"
        const val Material = "com.google.android.material:material:${Versions.material}"
    }

    object DaggerHilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    }

    object Test {
        const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

        const val Junit = "junit:junit:${Versions.jUnit}"
        const val JunitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    }

    object Modules {
        const val core = ":core"
    }

    object TestConfigurations {
        const val testImplementation = "testImplementationDependencies"
        const val androidTestImplementation = "androidTestImplementationDependencies"
    }
}