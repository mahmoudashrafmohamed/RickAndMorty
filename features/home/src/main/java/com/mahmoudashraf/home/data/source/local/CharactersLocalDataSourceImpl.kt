package com.mahmoudashraf.home.data.source.local

import com.mahmoudashraf.local.dao.CharactersDao
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(
    private val charactersDao: CharactersDao
) : CharactersLocalDataSource {
    override suspend fun getCharacters(page: Int): List<CharacterLocalEntity> {
        return charactersDao.getCharacters(page)
    }

    override fun deleteCharacters(): Int {
       return charactersDao.deleteCharacters()
    }

    override fun addCharacters(characters: List<CharacterLocalEntity>) {
        return charactersDao.addCharacters(characters)
    }
}