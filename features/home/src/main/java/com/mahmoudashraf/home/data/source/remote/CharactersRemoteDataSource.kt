package com.mahmoudashraf.home.data.source.remote

import com.orcas.entities.home.CharacterResponse

interface CharactersRemoteDataSource {
  suspend fun getCharacters(): CharacterResponse
}
