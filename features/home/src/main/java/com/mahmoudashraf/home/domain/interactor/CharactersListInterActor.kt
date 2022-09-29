package com.mahmoudashraf.home.domain.interactor

import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.mahmoudashraf.entities.home.CharacterResponse
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersListInterActor @Inject constructor(private val charactersRepository: CharactersRepository) {
  suspend fun getCharacters(page : Int): Flow<List<CharacterLocalEntity>> {
    return charactersRepository.getCharacters(page)
  }
}
