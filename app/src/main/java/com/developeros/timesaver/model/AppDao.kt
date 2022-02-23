package com.developeros.timesaver.model
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {
    @Insert
    fun InsertTask(saveDataModel: AppSaveDataModel)
    @Delete
    fun DeleteTask(saveDataModel: AppSaveDataModel)

    @Update
    fun UpdateTask(saveDataModel: AppSaveDataModel)

    @Query("SELECT * FROM time_saver_table")
    fun getAllTasks(): LiveData<List<AppSaveDataModel>>

}