package com.mahmoudashraf.home.data.mappers

import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.data.model.CharacterRemoteEntity
import com.mahmoudashraf.local.entities.CharacterLocalEntity

fun CharacterRemoteEntity.asCharacterEntity() = Character(id, name, image)
fun CharacterRemoteEntity.asCharacterLocalEntity(page : Int) = CharacterLocalEntity(id, image, name, page)
fun CharacterLocalEntity.asCharacterEntity() = Character(id, name, image)
