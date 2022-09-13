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

    object Test {
        const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

        const val Junit = "junit:junit:${Versions.jUnit}"
        const val JunitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    }
}