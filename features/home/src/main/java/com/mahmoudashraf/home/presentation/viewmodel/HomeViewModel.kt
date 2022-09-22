package com.mahmoudashraf.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interActor: CharactersListInterActor) : ViewModel() {
    init {
        viewModelScope.launch {
            interActor.getCharacters().collect {
                Log.e("collected....",""+it.data.size)
            }
        }
    }
}