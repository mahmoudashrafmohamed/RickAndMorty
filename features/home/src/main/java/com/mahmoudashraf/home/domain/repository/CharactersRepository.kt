package com.mahmoudashraf.home.domain.repository

import com.mahmoudashraf.entities.home.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
  suspend fun getCharacters(page: Int): Flow<List<Character>>
}
