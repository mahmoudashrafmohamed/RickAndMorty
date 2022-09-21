package com.mahmoudashraf.home.data.source.remote

import retrofit2.http.GET

interface CharactersService {
    @GET("character")
    suspend fun getCharacters(): List<Any>
}