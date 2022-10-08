package com.mahmoudashraf.local.common

import com.mahmoudashraf.local.datastore.PrefsDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UIModeInterActor @Inject constructor(
    private val prefsDataStore: PrefsDataStore
) {
    fun getUiModeData() : Flow<Boolean> = prefsDataStore.uiMode
    suspend fun saveUIMode(isNightMode : Boolean) = prefsDataStore.saveUIModeToDataStore(isNightMode)
}