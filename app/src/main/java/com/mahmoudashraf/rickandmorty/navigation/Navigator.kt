package com.mahmoudashraf.rickandmorty.navigation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.mahmoudashraf.rickandmorty.R
import com.mahmoudashraf.splash.SplashActions
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(
    private val navController: NavController,
) : SplashActions {
    override fun navigateToHome(someArg: String) {
        Log.e("sasasas", "000000000000000000000000")
        navController.navigate(R.id.homeFragment, Bundle()) // todo add create bundle
    }
}




