package com.mahmoudashraf.home.data.source.remote

import com.mahmoudashraf.home.data.model.CharacterRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
  @GET("character")
  suspend fun getCharacters(@Query("page") page: Int): CharacterRemoteModel
}
