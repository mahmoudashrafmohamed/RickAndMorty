package com.orcas.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orcas.local.constants.Constants
import com.orcas.local.entities.CharacterLocalEntity

@Dao
interface CharactersDao {
    @Query("SELECT * FROM ${Constants.CHARACTERS_TABLE_NAME}")
    fun getCharacters(): List<CharacterLocalEntity>

    @Query("DELETE FROM ${Constants.CHARACTERS_TABLE_NAME}")
    fun deleteCharacters(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(vararg character: CharacterLocalEntity)
}
