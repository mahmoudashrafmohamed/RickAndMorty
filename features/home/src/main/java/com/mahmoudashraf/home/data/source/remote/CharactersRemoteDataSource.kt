package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.entities.home.CharacterResponse

interface CharactersRemoteDataSource {
  suspend fun getCharacters(page : Int): CharacterResponse
}
