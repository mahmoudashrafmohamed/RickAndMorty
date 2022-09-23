package com.mahmoudashraf.home.data.di

import com.mahmoudashraf.home.data.repository.CharactersRepositoryImpl
import com.mahmoudashraf.home.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Singleton
  @Binds
  abstract fun bindCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository
}
