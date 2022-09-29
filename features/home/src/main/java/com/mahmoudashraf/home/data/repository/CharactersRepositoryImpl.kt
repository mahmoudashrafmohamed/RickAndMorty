package com.mahmoudashraf.home.data.repository

import android.util.Log
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import com.orcas.entities.home.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
  private val charactersRemoteDataSource: CharactersRemoteDataSource,
  private val charactersLocalDataSource: CharactersLocalDataSource
) : CharactersRepository {
  override suspend fun getCharacters(page : Int): Flow<CharacterResponse> {
    return flow {
      val response = charactersRemoteDataSource.getCharacters(page)
      val local = charactersLocalDataSource.getCharacters(1)
      Log.e("local",""+local.size)
      emit(response)
    }.flowOn(Dispatchers.IO)
  }
}
