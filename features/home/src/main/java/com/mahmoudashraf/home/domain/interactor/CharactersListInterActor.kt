package com.mahmoudashraf.home.domain.interactor

import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.orcas.entities.home.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersListInterActor @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend fun getCharacters(): Flow<CharacterResponse> {
        return charactersRepository.getCharacters()
    }
}