package com.mahmoudashraf.remote.services

import com.mahmoudashraf.remote.model.CharacterRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
  @GET("character")
  suspend fun getCharacters(@Query("page") page: Int): CharacterRemoteModel
}
