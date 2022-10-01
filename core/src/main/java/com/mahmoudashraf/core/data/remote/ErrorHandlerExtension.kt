package com.mahmoudashraf.core.data.remote

import com.mahmoudashraf.core.exceptions.RickAndMortyException
import retrofit2.HttpException

fun Throwable.toRickAndMortyException(): RickAndMortyException {
    return try {
        when (this) {
            is NoConnectivityException -> RickAndMortyException.NoConnection
            is HttpException -> {
                when (code()) {
                    400 -> RickAndMortyException.Business(message())
                    401 -> RickAndMortyException.UnAuthorized
                    404 -> RickAndMortyException.NotFound
                    else -> RickAndMortyException.ServerDown
                }
            }
            else -> RickAndMortyException.ServerDown
        }
    } catch (e: Exception) {
        RickAndMortyException.ServerDown
    }
}