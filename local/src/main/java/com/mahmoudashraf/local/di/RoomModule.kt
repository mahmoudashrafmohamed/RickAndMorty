package com.mahmoudashraf.local.di

import android.content.Context
import com.mahmoudashraf.local.dao.CharactersDao
import com.mahmoudashraf.local.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  @Provides
  @Singleton
  fun provideRoomDatabase(@ApplicationContext context: Context): CharactersDatabase {
    return CharactersDatabase.getInstance(context)
  }

  @Provides
  @Singleton
  fun provideCharactersDao(charactersDatabase: CharactersDatabase): CharactersDao {
    return charactersDatabase.charactersDao()
  }
}
