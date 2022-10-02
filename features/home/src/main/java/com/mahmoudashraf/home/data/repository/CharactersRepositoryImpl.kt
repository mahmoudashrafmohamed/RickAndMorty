package com.mahmoudashraf.home.data.repository

import android.util.Log
import com.mahmoudashraf.core.data.PrefsDataStore
import com.mahmoudashraf.home.data.mappers.asCharacterEntity
import com.mahmoudashraf.home.data.mappers.asCharacterLocalEntity
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val prefsDataStore: PrefsDataStore
) : CharactersRepository {
    override suspend fun getCharacters(page: Int) =
        flow {
            charactersLocalDataSource.getCharacters(page)
                .takeIf {
                    it.isNotEmpty() && shouldCallApi(
                        lastApiCallMillis = prefsDataStore.getLastCallApiTime().first()
                    ).not()
                }
                ?.let { characters ->
                    emit(characters.map { it.asCharacterEntity() })
                }
                ?: run {
                    charactersRemoteDataSource.getCharacters(page).let { response ->
                        charactersLocalDataSource.addCharacters(response.data.map {
                            it.asCharacterLocalEntity(
                                page
                            )
                        })
                        Log.e("update","db")
                        prefsDataStore.updateLastCallApiTime(System.currentTimeMillis())
                        emit(response.data.map { it.asCharacterEntity() })
                    }
                }

        }.flowOn(Dispatchers.IO)

    override fun getUiModeData() = prefsDataStore.getUiModeData()

    override suspend fun saveUIMode(isNightMode: Boolean) =
        prefsDataStore.saveUIModeToDataStore(isNightMode)

    private fun shouldCallApi(
        lastApiCallMillis: Long,
        cacheThresholdInMillis: Long = 300000L //default value is 5 minutes//
    ): Boolean {
        Log.e("shouldcallApi","called")
        return (System.currentTimeMillis() - lastApiCallMillis) >= cacheThresholdInMillis
    }

}
