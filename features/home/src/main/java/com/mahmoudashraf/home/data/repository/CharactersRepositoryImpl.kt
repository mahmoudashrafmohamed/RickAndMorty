package com.mahmoudashraf.home.data.repository

import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.orcas.entities.home.CharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override suspend fun getCharacters(): Flow<CharacterResponse> {
        return flow { charactersRemoteDataSource.getCharacters() }
    }
}