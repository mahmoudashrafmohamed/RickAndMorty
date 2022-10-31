package com.mahmoudashraf.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mahmoudashraf.core.androidextensions.isDarkMode
import com.mahmoudashraf.local.datastore.Keys.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PrefsDataStore {
  suspend fun updateLastCallApiTime(lastCallApiTime: Long)
  val lastCallApiTime: Flow<Long>
  suspend fun saveUIModeToDataStore(isNightMode: Boolean)
  val uiMode: Flow<Boolean>
}

class PrefsDataStoreImpl @Inject constructor(private val context: Context) : PrefsDataStore {

  private val Context.prefDataStore by preferencesDataStore(DATA_STORE_NAME)

  override suspend fun updateLastCallApiTime(lastCallApiTime: Long) {
    context.prefDataStore.edit { it[Keys.LAST_API_CALL_TIME] = lastCallApiTime }
  }

  override val lastCallApiTime
    get() = context.prefDataStore.data.map { preferences ->
      preferences[Keys.LAST_API_CALL_TIME] ?: 0L
    }

  override val uiMode: Flow<Boolean>
    get() = context.prefDataStore.data.map { preferences ->
      preferences[Keys.UI_MODE_KEY] ?: run {
        val isDark = context.isDarkMode()
        isDark
      }
    }

  override suspend fun saveUIModeToDataStore(isNightMode: Boolean) {
    context.prefDataStore.edit { preferences ->
      preferences[Keys.UI_MODE_KEY] = isNightMode
    }
  }
}
private object Keys {
  const val DATA_STORE_NAME = "rick_morty_data_store"
  val LAST_API_CALL_TIME = longPreferencesKey("show_completed")
  val UI_MODE_KEY = booleanPreferencesKey("ui_mode_key")
}
