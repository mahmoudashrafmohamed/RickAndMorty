package com.mahmoudashraf.home.data.source.remote

interface CharactersRemoteDataSource {
    suspend fun getCharacters(): List<Any>
}