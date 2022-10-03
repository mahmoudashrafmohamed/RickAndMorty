package com.mahmoudashraf.entities.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val gender: String,
    val species: String,
    val status : String,
    val locationText : String
) : Parcelable
