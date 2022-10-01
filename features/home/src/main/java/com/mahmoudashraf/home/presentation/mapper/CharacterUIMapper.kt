package com.mahmoudashraf.home.presentation.mapper

import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.presentation.model.BaseCharacterUIModel
import com.mahmoudashraf.home.presentation.model.CharacterUIModel

fun List<Character>.asUIModel(): List<BaseCharacterUIModel> {
    return this.map {
        CharacterUIModel(it.id, it.name, it.image)
    }
}