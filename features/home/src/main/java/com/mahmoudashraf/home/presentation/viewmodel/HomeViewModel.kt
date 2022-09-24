package com.mahmoudashraf.home.presentation.viewmodel

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

    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val uiState: StateFlow<HomeScreenState> = _uiState

    init {
        getCharacters()
    }

    fun getCharacters() {
        _uiState.value = HomeScreenState.Loading
        viewModelScope.launch {
            interActor.getCharacters()
                .catch { t ->
                    t.printStackTrace()
                    _uiState.value = HomeScreenState.Error(t.message ?: "error!")
                }
                .collect {
                    _uiState.value = HomeScreenState.Success(it.data)
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
