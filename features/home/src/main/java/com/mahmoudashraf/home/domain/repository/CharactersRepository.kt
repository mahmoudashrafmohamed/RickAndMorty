package com.mahmoudashraf.home.domain.repository

import com.mahmoudashraf.entities.home.CharacterResponse
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
  suspend fun getCharacters(page : Int): Flow<List<CharacterLocalEntity>>
}
