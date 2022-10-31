package com.mahmoudashraf.splash

import androidx.lifecycle.ViewModel
import com.mahmoudashraf.local.common.UIModeInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val uiModeInterActor: UIModeInterActor) : ViewModel() {
  // get UI mode
  val uiMode = uiModeInterActor.getUiModeData()
}
