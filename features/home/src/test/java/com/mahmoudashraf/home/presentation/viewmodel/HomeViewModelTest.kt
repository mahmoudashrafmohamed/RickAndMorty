package com.mahmoudashraf.home.presentation.viewmodel

import com.mahmoudashraf.home.domain.interactor.CharactersListInterActor
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.presentation.mapper.asUIModel
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
        val data =  listOf<Character>()
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters(1)).thenReturn(flow { emit(data) })
        val viewModel = HomeViewModel(charactersListInterActor)
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
        val data =  listOf<Character>()
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters(1))
            .thenReturn(flow { throw Throwable("error!") })
        val viewModel = HomeViewModel(charactersListInterActor)
        // act
        viewModel.getCharacters(1)
        val state = viewModel.uiState.value
        // assert
        assertEquals(HomeScreenState.Error("error!"), state)
        Dispatchers.resetMain()
    }

    @Test
    fun when_triggerGetCharacters_then_returnLoading() = runTest {
        // arrange
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val data =  listOf<Character>()
        val charactersListInterActor = Mockito.mock(CharactersListInterActor::class.java)
        Mockito.`when`(charactersListInterActor.getCharacters(1)).then {
            flow {
                withContext(Dispatchers.Unconfined) {
                    delay(1000)
                }
                   emit(data)
                }
        }
        val viewModel = HomeViewModel(charactersListInterActor)
        viewModel.getCharacters(1)
        val state = viewModel.uiState.value
        // assert
        assertEquals(HomeScreenState.Loading, state)
        Dispatchers.resetMain()
    }


}