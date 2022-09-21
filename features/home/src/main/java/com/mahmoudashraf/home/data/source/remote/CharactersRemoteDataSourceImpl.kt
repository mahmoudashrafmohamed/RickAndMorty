package com.mahmoudashraf.home.data.source.remote

import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(private val charactersService: CharactersService) : CharactersRemoteDataSource {
    override suspend fun getCharacters(): List<Any> {
        return charactersService.getCharacters()
    }
}