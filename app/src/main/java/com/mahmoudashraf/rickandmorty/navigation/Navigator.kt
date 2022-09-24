package com.mahmoudashraf.rickandmorty.navigation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.mahmoudashraf.home.presentation.view.navigation.HomeActions
import com.mahmoudashraf.rickandmorty.R
import com.mahmoudashraf.splash.SplashActions
import com.orcas.entities.home.Character
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(
  private val navController: NavController
) : SplashActions, HomeActions {
  override fun navigateToHome(someArg: String) {
    Log.e("sasasas", "000000000000000000000000")
    navController.popBackStack()
    navController.navigate(R.id.homeFragment, Bundle()) // todo add create bundle
  }

  override fun navigateToDetails(character: Character) {
   Log.e("nav to: ","details")

  }
}
