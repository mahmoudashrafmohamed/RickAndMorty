package com.mahmoudashraf.core.androidextensions

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatDelegate

fun Context.isInternetAvailable(): Boolean {
  val connectivityManager =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val networkCapabilities = connectivityManager.activeNetwork ?: return false
  val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
  return when {
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
    else -> false
  }
}

// check dark mode or not
fun Context.isDarkMode(): Boolean {
  val defaultNightMode = AppCompatDelegate.getDefaultNightMode()
  if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
    return true
  }
  if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_NO) {
    return false
  }
  when ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)) {
    Configuration.UI_MODE_NIGHT_NO -> return false
    Configuration.UI_MODE_NIGHT_YES -> return true
    Configuration.UI_MODE_NIGHT_UNDEFINED -> return false
  }
  return false
}
