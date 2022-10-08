package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.remote.model.CharacterRemoteModel
import com.mahmoudashraf.remote.services.CharactersService
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersService: CharactersService) : CharactersRemoteDataSource {
  override suspend fun getCharacters(page : Int): CharacterRemoteModel {
    return charactersService.getCharacters(page)
  }
}
