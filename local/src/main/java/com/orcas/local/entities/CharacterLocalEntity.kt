package com.orcas.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orcas.local.constants.Constants

@Entity(tableName = Constants.CHARACTERS_TABLE_NAME)
data class CharacterLocalEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
)
