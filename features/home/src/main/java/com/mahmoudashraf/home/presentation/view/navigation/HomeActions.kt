package com.mahmoudashraf.home.presentation.view.navigation

import com.mahmoudashraf.entities.home.Character

interface HomeActions {
    fun navigateToDetails(character: Character)
    fun navigateToAbout()
}
