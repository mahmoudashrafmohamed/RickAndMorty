package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.home.data.model.CharacterRemoteModel


interface CharactersRemoteDataSource {
  suspend fun getCharacters(page : Int): CharacterRemoteModel
}
