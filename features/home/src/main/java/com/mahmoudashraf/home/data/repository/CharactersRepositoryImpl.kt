package com.mahmoudashraf.home.data.repository

import android.util.Log
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource
) : CharactersRepository {
    override suspend fun getCharacters(page: Int) = flow {
        val localCharacters = charactersLocalDataSource.getCharacters(page)
        if (localCharacters.isEmpty()) {
            val response = charactersRemoteDataSource.getCharacters(page)
            val mappedToLocal = response.data.map { it.asCharacterLocalEntity() }
            charactersLocalDataSource.addCharacters(mappedToLocal)
            emit(mappedToLocal)
        } else {
            emit(localCharacters)
        }
    }.flowOn(Dispatchers.IO)
}


fun Character.asCharacterLocalEntity() = CharacterLocalEntity(id, image, name)
