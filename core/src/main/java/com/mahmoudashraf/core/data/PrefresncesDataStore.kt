package com.mahmoudashraf.core.data

import android.content.Context
import android.util.Log
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
}

class PrefsDataStoreImpl @Inject constructor(private val context: Context) : PrefsDataStore {

    private val Context.prefDataStore by preferencesDataStore(CoreConstants.DATA_STORE_NAME)


    override suspend fun updateLastCallApiTime(lastCallApiTime: Long) {
        Log.e("updated",""+lastCallApiTime)
        context.prefDataStore.edit { it[Keys.lastCallApiTime] = lastCallApiTime }
    }

    override fun getLastCallApiTime() = context.prefDataStore.data.map {
        it[Keys.lastCallApiTime] ?: 0L
    }
}

private object Keys {
    val lastCallApiTime = longPreferencesKey("show_completed")
}

