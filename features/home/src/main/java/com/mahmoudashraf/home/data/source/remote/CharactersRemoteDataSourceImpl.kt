package com.mahmoudashraf.home.data.source.remote

import com.orcas.entities.home.CharacterResponse
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersService: CharactersService) : CharactersRemoteDataSource {
    override suspend fun getCharacters(): CharacterResponse {
        return charactersService.getCharacters()
    }
}