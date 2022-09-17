package com.mahmoudashraf.rickandmorty.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mahmoudashraf.rickandmorty.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NavControllerModule {
    @Provides
    fun navController(activity: FragmentActivity): NavController {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}