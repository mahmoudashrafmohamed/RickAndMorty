package com.mahmoudashraf.home.presentation.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interActor: CharactersListInterActor) :
    ViewModel() {

    private lateinit var state: Parcelable
    private val cachedCharactersList = mutableListOf<CharacterLocalEntity>()
    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val uiState: StateFlow<HomeScreenState> = _uiState

    init {
        getCharacters(1)
    }

    fun getCharacters(page: Int) {
        _uiState.value = HomeScreenState.Loading
        viewModelScope.launch {
            interActor.getCharacters(page)
                .catch { t ->
                    t.printStackTrace()
                    _uiState.value = HomeScreenState.Error(t.message ?: "error!")
                }
                .collect {
                    cachedCharactersList.addAll(it)
                    _uiState.value = HomeScreenState.Success(cachedCharactersList)
                }
        }
    }

    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized
}

sealed class HomeScreenState {
    object Initial : HomeScreenState()
    object Loading : HomeScreenState()
    data class Success(val characters: List<CharacterLocalEntity>) : HomeScreenState()
    data class Error(val msg: String) : HomeScreenState()
}
