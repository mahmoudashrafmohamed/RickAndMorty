package com.mahmoudashraf.home.presentation.viewmodel

import com.mahmoudashraf.core.data.remote.NoConnectivityException
import com.mahmoudashraf.core.exceptions.RickAndMortyException
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.mahmoudashraf.home.presentation.mapper.asUIModel
import com.mahmoudashraf.local.common.UIModeInterActor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

  @Test
  fun when_triggerGetCharacters_then_returnItems() = runTest {
    // arrange
    val testDispatcher = UnconfinedTestDispatcher(testScheduler)
    Dispatchers.setMain(testDispatcher)
    val data = listOf<Character>()
    val uiModeInterActor = Mockito.mock(UIModeInterActor::class.java)
    val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
    Mockito.`when`(charactersListInterActor.getCharacters(1)).thenReturn(flow { emit(data) })
    val viewModel = HomeViewModel(charactersListInterActor, uiModeInterActor)
    // act
    viewModel.getCharacters(1)
    val state = viewModel.uiState.value
    // assert
    assertEquals(HomeScreenState.Success(data.asUIModel()), state)
    Dispatchers.resetMain()
  }

  @Test
  fun when_triggerGetCharacters_then_returnError() = runTest {
    // arrange
    val testDispatcher = UnconfinedTestDispatcher(testScheduler)
    Dispatchers.setMain(testDispatcher)
    val uiModeInterActor = Mockito.mock(UIModeInterActor::class.java)
    val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
    Mockito.`when`(charactersListInterActor.getCharacters(1))
      .thenReturn(flow { throw NoConnectivityException() })
    val viewModel = HomeViewModel(charactersListInterActor, uiModeInterActor)
    // act
    viewModel.getCharacters(1)
    val state = viewModel.uiState.value
    // assert
    assertEquals(HomeScreenState.Error(RickAndMortyException.NoConnection), state)
    Dispatchers.resetMain()
  }

  @Test
  fun when_triggerGetCharacters_then_returnLoading() = runTest {
    // arrange
    val testDispatcher = UnconfinedTestDispatcher(testScheduler)
    Dispatchers.setMain(testDispatcher)
    val data = listOf<Character>()
    val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
    val uiModeInterActor = Mockito.mock(UIModeInterActor::class.java)
    Mockito.`when`(charactersListInterActor.getCharacters(1)).then {
      flow {
        withContext(Dispatchers.Unconfined) {
          delay(1000)
        }
        emit(data)
      }
    }
    val viewModel = HomeViewModel(charactersListInterActor, uiModeInterActor)
    viewModel.getCharacters(1)
    val state = viewModel.uiState.value
    // assert
    assertEquals(HomeScreenState.Loading, state)
    Dispatchers.resetMain()
  }
}
