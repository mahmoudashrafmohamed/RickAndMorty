package com.mahmoudashraf.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoudashraf.local.constants.Constants

@Entity(tableName = Constants.CHARACTERS_TABLE_NAME)
data class CharacterLocalEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
)
