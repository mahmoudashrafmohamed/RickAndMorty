package com.mahmoudashraf.home.domain.interactor

import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersListInterActor @Inject constructor(private val charactersRepository: CharactersRepository) {
  suspend fun getCharacters(page : Int): Flow<List<Character>> {
    return charactersRepository.getCharacters(page)
  }
}
