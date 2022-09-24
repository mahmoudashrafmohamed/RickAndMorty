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
        const val LifecycleKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val NavigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navUiKtx}"
        const val NavigationFragment = "androidx.navigation:navigation-fragment:${Versions.navFragment}"
        const val ActivityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val FragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.fragmentKtx}"
    }

    object DaggerHilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Skeleton {
       const val Androidveil = "com.github.skydoves:androidveil:${Versions.androidVeil}"
    }

    object Test {
        const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

        const val Junit = "junit:junit:${Versions.jUnit}"
        const val JunitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    }

    object Modules {
        const val core = ":core"
        const val entities = ":entities"
        const val splash = ":features:splash"
        const val home = ":features:home"
        const val details = ":features:details"
    }

    object TestConfigurations {
        const val testImplementation = "testImplementationDependencies"
        const val androidTestImplementation = "androidTestImplementationDependencies"
    }
}