package com.orcas.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orcas.local.constants.Constants
import com.orcas.local.dao.CharactersDao
import com.orcas.local.entities.CharacterLocalEntity

@Database(
    entities = [CharacterLocalEntity::class],
    version = Constants.DB_VERSION,
    exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getInstance(context: Context): CharactersDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CharactersDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }
}