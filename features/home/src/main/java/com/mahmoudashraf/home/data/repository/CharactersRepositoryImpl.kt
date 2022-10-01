package com.mahmoudashraf.home.data.repository

import android.util.Log
import com.mahmoudashraf.core.data.PrefsDataStore
import com.mahmoudashraf.home.data.mappers.asCharacterEntity
import com.mahmoudashraf.home.data.mappers.asCharacterLocalEntity
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val prefsDataStore: PrefsDataStore
) : CharactersRepository {
    override suspend fun getCharacters(page: Int) =
        flow {
            charactersLocalDataSource.getCharacters(page)
                .takeIf {false}// it.isNotEmpty() && shouldCallApi(lastApiCallMillis = getLastCallApiMillis()).not() }
                ?.let { characters ->
                    emit(characters.map { it.asCharacterEntity() }) }
                ?: run {
                    kotlinx.coroutines.delay(5000)
                    charactersRemoteDataSource.getCharacters(page).let { response ->
                        charactersLocalDataSource.addCharacters(response.data.map { it.asCharacterLocalEntity() })
                        prefsDataStore.updateLastCallApiTime(System.currentTimeMillis())
                        emit(response.data.map { it.asCharacterEntity() })
                    }
                }

        }.flowOn(Dispatchers.IO)

    private fun getLastCallApiMillis(): Long {
        val lastApiCallMillis: Long
        runBlocking {
            lastApiCallMillis = prefsDataStore.getLastCallApiTime().first()
            Log.e("current,---", "" + lastApiCallMillis)
        }
        return lastApiCallMillis
    }

    private fun shouldCallApi(
        lastApiCallMillis: Long,
        cacheThresholdInMillis: Long = 300000L //default value is 5 minutes//
    ): Boolean {
        return (System.currentTimeMillis() - lastApiCallMillis) >= cacheThresholdInMillis
    }

}
