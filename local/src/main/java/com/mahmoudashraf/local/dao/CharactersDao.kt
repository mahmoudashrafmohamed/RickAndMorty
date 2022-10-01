package com.mahmoudashraf.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoudashraf.local.constants.Constants
import com.mahmoudashraf.local.entities.CharacterLocalEntity

@Dao
interface CharactersDao {
    @Query("SELECT * FROM ${Constants.CHARACTERS_TABLE_NAME} WHERE page = :page")
    fun getCharacters(page: Int): List<CharacterLocalEntity>

    @Query("DELETE FROM ${Constants.CHARACTERS_TABLE_NAME}")
    fun deleteCharacters(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacters(character: List<CharacterLocalEntity>)
}
