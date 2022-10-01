package com.mahmoudashraf.core.di

import android.content.Context
import com.mahmoudashraf.core.data.PrefsDataStore
import com.mahmoudashraf.core.data.PrefsDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefsDataStoreModule {
    @Provides
    @Singleton
    fun provideCharactersService(@ApplicationContext context: Context): PrefsDataStore {
        return PrefsDataStoreImpl(context)
    }
}
