package com.mahmoudashraf.rickandmorty.navigation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.mahmoudashraf.core.constants.NavigationConstants
import com.mahmoudashraf.home.presentation.view.navigation.HomeActions
import com.mahmoudashraf.rickandmorty.R
import com.mahmoudashraf.splash.SplashActions
import com.mahmoudashraf.entities.home.Character
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(
  private val navController: NavController
) : SplashActions, HomeActions {
  override fun navigateToHome() {
    navController.popBackStack()
    navController.navigate(R.id.homeFragment)
  }

  override fun navigateToDetails(character: Character) {
    navController.navigate(R.id.action_home_fragment_to_details_nav_graph,Bundle().apply {
      putParcelable(NavigationConstants.CHARACTER,character)
    })
  }
}
