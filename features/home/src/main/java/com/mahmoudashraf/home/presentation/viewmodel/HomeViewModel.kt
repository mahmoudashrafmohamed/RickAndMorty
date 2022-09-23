package com.mahmoudashraf.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.orcas.entities.home.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interActor: CharactersListInterActor) :
  ViewModel() {

  private val _characters = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
  val characters: StateFlow<HomeScreenState> = _characters

  init {
      getCharacters()
  }

  private fun getCharacters() {
    Log.e("fetch,","........")
    _characters.value = HomeScreenState.Loading
    viewModelScope.launch {
      interActor.getCharacters()
        .catch { t ->
          t.printStackTrace()
          _characters.value = HomeScreenState.Error(t.message ?: "error!")
        }
        .collect {
          _characters.value = HomeScreenState.Success(it.data)
        }
    }
  }
}

sealed class HomeScreenState {
  object Initial : HomeScreenState()
  object Loading : HomeScreenState()
  data class Success(val characters: List<Character>) : HomeScreenState()
  data class Error(val msg: String) : HomeScreenState()
}
