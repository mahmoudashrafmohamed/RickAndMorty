package com.mahmoudashraf.home.data.source.remote

class CharactersRemoteDataSourceImpl : CharactersRemoteDataSource {
    override suspend fun getCharacters(): List<Any> {
        return listOf()
    }
}