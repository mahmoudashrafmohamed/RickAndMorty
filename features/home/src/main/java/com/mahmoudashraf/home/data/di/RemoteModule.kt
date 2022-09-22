package com.mahmoudashraf.home.data.di

import com.mahmoudashraf.core.BuildConfig
import com.mahmoudashraf.core.data.remote.ApiServiceFactory
import com.mahmoudashraf.home.data.source.remote.CharactersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideBlogService(): CharactersService {
        return ApiServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }
}