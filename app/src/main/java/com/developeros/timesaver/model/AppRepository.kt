package com.developeros.timesaver.model

import android.content.Context
import android.provider.Settings
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.developeros.timesaver.R

class AppRepository(val appDao: AppDao) {

    var allTasks :LiveData<List<AppSaveDataModel>> = appDao.getAllTasks()

    @WorkerThread
    suspend fun Insert(saveDataModel: AppSaveDataModel){
        if(saveDataModel.Category=="0"){
            appDao.InsertTask(saveDataModel)
        }else{

            appDao.InsertTask(saveDataModel)
        }
    }
    @WorkerThread
    suspend fun Delete(saveDataModel: AppSaveDataModel){
        appDao.DeleteTask(saveDataModel)
    }
    @WorkerThread
    suspend fun Update(saveDataModel: AppSaveDataModel){
        appDao.UpdateTask(saveDataModel)
    }
}