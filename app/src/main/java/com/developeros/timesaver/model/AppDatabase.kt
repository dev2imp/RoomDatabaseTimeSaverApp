package com.developeros.timesaver.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
@Database(entities = arrayOf(AppSaveDataModel::class), version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun appDao():AppDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "time_saver_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}