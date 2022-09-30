package com.mahmoudashraf.entities.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Characters(
    val data: List<Character>
)

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable
