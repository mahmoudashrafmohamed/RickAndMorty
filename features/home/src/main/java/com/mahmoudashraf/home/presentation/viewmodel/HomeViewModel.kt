package com.mahmoudashraf.home.presentation.viewmodel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.mahmoudashraf.home.presentation.model.BaseCharacterUIModel
import com.mahmoudashraf.home.presentation.model.CharacterUIModel
import com.mahmoudashraf.home.presentation.model.LoadingItemUIModel
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
    private val cachedCharactersList = mutableListOf<Character>()
    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val uiState: StateFlow<HomeScreenState> = _uiState
    private var pageNo = 1
    private var isLoading = false

    init {
        getCharacters()
    }

    fun getCharacters(page: Int = pageNo) {
        if (isLoading) return
        isLoading = true
        if (page == 1)
            _uiState.value = HomeScreenState.Loading
        else {
            val uiList = cachedCharactersList.asUIModel().toMutableList()
            uiList.add(LoadingItemUIModel(-1))
            _uiState.value = HomeScreenState.LoadingNextPage(uiList)
        }
        viewModelScope.launch {
            interActor.getCharacters(page)
                .catch { t ->
                    t.printStackTrace()
                    _uiState.value = HomeScreenState.Error(t.message ?: "error!")
                }
                .collect {
                    cachedCharactersList.addAll(it)
                    _uiState.value = HomeScreenState.Success(cachedCharactersList.asUIModel())
                    pageNo++
                    isLoading = false
                }
        }
    }

    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized
}

private fun List<Character>.asUIModel(): List<BaseCharacterUIModel> {
    return this.map {
        CharacterUIModel(it.id, it.name, it.image)
    }
}

sealed class HomeScreenState {
    object Initial : HomeScreenState()
    object Loading : HomeScreenState()
    data class LoadingNextPage(val characters: List<BaseCharacterUIModel>) : HomeScreenState()
    data class Success(val characters: List<BaseCharacterUIModel>) : HomeScreenState()
    data class Error(val msg: String) : HomeScreenState()
}
