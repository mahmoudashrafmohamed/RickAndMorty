package com.mahmoudashraf.home.data.source.local

import com.mahmoudashraf.local.entities.CharacterLocalEntity

interface CharactersLocalDataSource {
    suspend fun getCharacters(page : Int): List<CharacterLocalEntity>
    fun addCharacter(character: List<CharacterLocalEntity>)
}