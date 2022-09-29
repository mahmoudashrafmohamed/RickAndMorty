package com.mahmoudashraf.home.data.di

import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSource
import com.mahmoudashraf.home.data.source.local.CharactersLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {
  @Singleton
  @Binds
  abstract fun bindCharactersLocalDataSource(impl: CharactersLocalDataSourceImpl): CharactersLocalDataSource
}
