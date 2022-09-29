package com.mahmoudashraf.home.data.source.local

import com.orcas.local.dao.CharactersDao
import com.orcas.local.entities.CharacterLocalEntity
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(
    private val charactersDao: CharactersDao
) : CharactersLocalDataSource {
    override suspend fun getCharacters(page: Int): List<CharacterLocalEntity> {
        return charactersDao.getCharacters()
    }
}