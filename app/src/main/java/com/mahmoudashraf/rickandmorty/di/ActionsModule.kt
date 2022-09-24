package com.mahmoudashraf.rickandmorty.di

import com.mahmoudashraf.home.presentation.view.navigation.HomeActions
import com.mahmoudashraf.rickandmorty.navigation.Navigator
import com.mahmoudashraf.splash.SplashActions
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActionsModule {
  @Binds
  abstract fun splash(navigator: Navigator): SplashActions

  @Binds
  abstract fun home(navigator: Navigator): HomeActions
}
