package com.mahmoudashraf.core.exceptions


sealed class RickAndMortyException : Throwable() {
    object NoConnection : RickAndMortyException()
    object UnAuthorized : RickAndMortyException()
    object NotFound : RickAndMortyException()
    data class Business(val msg: String) : RickAndMortyException()
    object ServerDown : RickAndMortyException()
}
