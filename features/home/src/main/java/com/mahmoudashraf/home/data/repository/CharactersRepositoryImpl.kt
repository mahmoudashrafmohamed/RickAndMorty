package com.mahmoudashraf.home.data.repository

import android.util.Log
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.mahmoudashraf.entities.home.CharacterResponse
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
  private val charactersRemoteDataSource: CharactersRemoteDataSource,
  private val charactersLocalDataSource: CharactersLocalDataSource
) : CharactersRepository {
  override suspend fun getCharacters(page : Int): Flow<List<CharacterLocalEntity>> {
    return flow {
      val localCharacters = charactersLocalDataSource.getCharacters(page)
      Log.e("local",""+localCharacters.size)
      if (localCharacters.isEmpty()){
        val response = charactersRemoteDataSource.getCharacters(page)
        val mappedToLocal = response.data.map { CharacterLocalEntity(it.id,it.image,it.name) }
         charactersLocalDataSource.addCharacter(mappedToLocal)
        emit(mappedToLocal)
      }
      else {
        emit(localCharacters)
      }
    }
      .flowOn(Dispatchers.IO)
  }
}
