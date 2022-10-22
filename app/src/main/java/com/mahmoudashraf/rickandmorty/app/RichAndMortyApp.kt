package com.mahmoudashraf.rickandmorty.app

import android.app.Application
import com.mahmoudashraf.core.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class RichAndMortyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}
