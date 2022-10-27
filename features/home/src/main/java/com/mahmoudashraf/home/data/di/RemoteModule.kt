package com.mahmoudashraf.home.data.di

import android.content.Context
import com.mahmoudashraf.core.BuildConfig
import com.mahmoudashraf.core.data.remote.ApiServiceFactory
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSource
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSourceImpl
import com.mahmoudashraf.nativelib.NativeLib
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideCharactersService(@ApplicationContext context: Context): com.mahmoudashraf.remote.services.CharactersService {
    return ApiServiceFactory.create(BuildConfig.DEBUG,context,  NativeLib.baseUrl())
  }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
  @Singleton
  @Binds
  abstract fun bindCharactersRemoteDataSource(impl: CharactersRemoteDataSourceImpl): CharactersRemoteDataSource
}
