package com.mahmoudashraf.home.data.source.remote

import com.orcas.entities.home.CharacterResponse
import retrofit2.http.GET

interface CharactersService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}