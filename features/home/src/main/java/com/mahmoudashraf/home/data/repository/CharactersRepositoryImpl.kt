package com.mahmoudashraf.home.data.repository

import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.data.model.CharacterRemoteEntity
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
        charactersLocalDataSource.getCharacters(page)
            .takeIf { it.isNotEmpty() }
            ?.let { characters -> emit(characters.map { it.asCharacterEntity() }) }
            ?: run {
                charactersRemoteDataSource.getCharacters(page).let { response ->
                    charactersLocalDataSource.addCharacters(response.data.map { it.asCharacterLocalEntity() })
                    emit(response.data.map { it.asCharacterEntity() })
                }
            }
    }.flowOn(Dispatchers.IO)
}


fun CharacterRemoteEntity.asCharacterEntity() = Character(id, name, image)
fun CharacterRemoteEntity.asCharacterLocalEntity() = CharacterLocalEntity(id, image, name)
fun CharacterLocalEntity.asCharacterEntity() = Character(id, name, image)
