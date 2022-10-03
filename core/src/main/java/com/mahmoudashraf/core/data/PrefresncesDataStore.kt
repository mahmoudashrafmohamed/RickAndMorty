package com.mahmoudashraf.core.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mahmoudashraf.core.androidextensions.isDarkMode
import com.mahmoudashraf.core.constants.CoreConstants
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

    private val Context.prefDataStore by preferencesDataStore(CoreConstants.DATA_STORE_NAME)


    override suspend fun updateLastCallApiTime(lastCallApiTime: Long) {
        Log.e("updated", "" + lastCallApiTime)
        context.prefDataStore.edit { it[Keys.LAST_API_CALL_TIME] = lastCallApiTime }
    }

    override val lastCallApiTime
        get() = context.prefDataStore.data.map { preferences ->
            preferences[Keys.LAST_API_CALL_TIME] ?: 0L
        }

    override val uiMode: Flow<Boolean>
        get() = context.prefDataStore.data.map { preferences ->
            preferences[Keys.UI_MODE_KEY] ?: context.isDarkMode()
        }

    override suspend fun saveUIModeToDataStore(isNightMode: Boolean) {
        context.prefDataStore.edit { preferences ->
            preferences[Keys.UI_MODE_KEY] = isNightMode
        }
    }
}

private object Keys {
    val LAST_API_CALL_TIME = longPreferencesKey("show_completed")
    val UI_MODE_KEY = booleanPreferencesKey("ui_mode_key")
}


