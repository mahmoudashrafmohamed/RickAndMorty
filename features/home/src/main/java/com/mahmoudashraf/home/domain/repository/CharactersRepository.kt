package com.mahmoudashraf.home.domain.repository

import com.orcas.entities.home.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
  suspend fun getCharacters(): Flow<CharacterResponse>
}
