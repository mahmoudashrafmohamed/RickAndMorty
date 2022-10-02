package com.mahmoudashraf.core.data

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mahmoudashraf.core.constants.CoreConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface PrefsDataStore {
    suspend fun updateLastCallApiTime(lastCallApiTime: Long)
    fun getLastCallApiTime(): Flow<Long>
    fun getUiModeData(): Flow<Boolean>
    suspend fun saveUIModeToDataStore(isNightMode: Boolean)
}

class PrefsDataStoreImpl @Inject constructor(private val context: Context) : PrefsDataStore {

    private val Context.prefDataStore by preferencesDataStore(CoreConstants.DATA_STORE_NAME)


    override suspend fun updateLastCallApiTime(lastCallApiTime: Long) {
        Log.e("updated", "" + lastCallApiTime)
        context.prefDataStore.edit { it[Keys.LAST_API_CALL_TIME] = lastCallApiTime }
    }

    override fun getLastCallApiTime() = context.prefDataStore.data.map {
        it[Keys.LAST_API_CALL_TIME] ?: 0L
    }

    override fun getUiModeData(): Flow<Boolean> {
        return context.prefDataStore.data.map { preferences ->
            val uiMode = preferences[Keys.UI_MODE_KEY] ?: isDarkMode()
            uiMode
        }
    }

    override suspend fun saveUIModeToDataStore(isNightMode: Boolean) {
        context.prefDataStore.edit { preferences ->
            preferences[Keys.UI_MODE_KEY] = isNightMode
        }
    }

    //check dark mode or not
    fun isDarkMode(): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO ->
                false
            Configuration.UI_MODE_NIGHT_YES ->
                true
            else -> false
        }
    }
}

private object Keys {
    val LAST_API_CALL_TIME = longPreferencesKey("show_completed")
    val UI_MODE_KEY = booleanPreferencesKey("ui_mode_key")
}


