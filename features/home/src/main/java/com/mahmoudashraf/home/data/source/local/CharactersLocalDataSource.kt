package com.mahmoudashraf.home.data.source.local

import com.orcas.local.entities.CharacterLocalEntity

interface CharactersLocalDataSource {
    suspend fun getCharacters(page : Int): List<CharacterLocalEntity>
}