package com.mahmoudashraf.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoudashraf.local.constants.Constants

@Entity(tableName = Constants.CHARACTERS_TABLE_NAME)
data class CharacterLocalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "page")
    val page: Int,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "location")
    val location: String,
)
