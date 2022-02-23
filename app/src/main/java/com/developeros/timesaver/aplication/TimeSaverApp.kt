package com.developeros.timesaver.aplication

import android.app.Application
import com.developeros.timesaver.model.AppDatabase
import com.developeros.timesaver.model.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TimeSaverApp:Application() {
    val applicationScope= CoroutineScope(SupervisorJob())
    val database by lazy {
        AppDatabase.getDatabase(this,applicationScope)
    }
    val repository by lazy {
        AppRepository(database.appDao())
    }
}