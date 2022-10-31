package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.remote.model.CharacterRemoteModel

interface CharactersRemoteDataSource {
  suspend fun getCharacters(page: Int): CharacterRemoteModel
}
