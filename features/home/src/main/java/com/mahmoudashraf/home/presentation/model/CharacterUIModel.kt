package com.mahmoudashraf.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

open class BaseCharacterUIModel(open val id : Int)

@Parcelize
data class CharacterUIModel(
    override val id: Int,
    val name: String,
    val image: String
) : Parcelable, BaseCharacterUIModel(id)

data class LoadingItemUIModel(
    override val id: Int
) :  BaseCharacterUIModel(id)
