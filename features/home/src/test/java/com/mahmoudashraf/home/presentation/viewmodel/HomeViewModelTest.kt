package com.mahmoudashraf.home.presentation.viewmodel

import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.orcas.entities.home.Character
import com.orcas.entities.home.CharacterResponse
import com.orcas.entities.home.Info
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
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
        val data = CharacterResponse(data = listOf<Character>(), info = Info(1, 1, "2", null))
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters()).thenReturn(flow { emit(data) })
        val viewModel = HomeViewModel(charactersListInterActor)
        // act
        viewModel.getCharacters()
        val state = viewModel.characters.value
        // assert
        assertEquals(HomeScreenState.Success(data.data), state)
        Dispatchers.resetMain()
    }

    @Test
    fun when_triggerGetCharacters_then_returnError() = runTest {
        // arrange
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val data = CharacterResponse(data = listOf<Character>(), info = Info(1, 1, "2", null))
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters())
            .thenReturn(flow { throw Throwable("error!") })
        val viewModel = HomeViewModel(charactersListInterActor)
        // act
        viewModel.getCharacters()
        val state = viewModel.characters.value
        // assert
        assertEquals(HomeScreenState.Error("error!"), state)
        Dispatchers.resetMain()
    }

    @Test
    fun when_triggerGetCharacters_then_returnLoading() = runTest {
        // arrange
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val data = CharacterResponse(data = listOf<Character>(), info = Info(1, 1, "2", null))
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters()).then {
            flow {
                withContext(Dispatchers.Unconfined) {
                    delay(1000)
                }
                   emit(data)
                }
        }
        val viewModel = HomeViewModel(charactersListInterActor)
        viewModel.getCharacters()
        val state = viewModel.characters.value
        // assert
        assertEquals(HomeScreenState.Loading, state)
        Dispatchers.resetMain()
    }


}