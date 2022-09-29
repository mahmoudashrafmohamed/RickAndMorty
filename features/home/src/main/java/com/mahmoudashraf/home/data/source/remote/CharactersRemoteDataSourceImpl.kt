package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.entities.home.CharacterResponse
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersService: CharactersService) : CharactersRemoteDataSource {
  override suspend fun getCharacters(page : Int): CharacterResponse {
    return charactersService.getCharacters(page)
  }
}
