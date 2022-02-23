package com.developeros.timesaver.viewmodel
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developeros.timesaver.R
import com.developeros.timesaver.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelClass(val appRepository: AppRepository): ViewModel() {
    var allTasks:LiveData<List<AppSaveDataModel>> =appRepository.allTasks
    fun insert(appSaveDataModel: AppSaveDataModel)=viewModelScope.launch(Dispatchers.IO) {
        appRepository.Insert(appSaveDataModel)
    }
    fun delete(index:Int)=viewModelScope.launch(Dispatchers.IO) {
        appRepository.allTasks.value?.let { appRepository.Delete(it.get(index)) }
    }
    fun RunOrStop(index:Int,running:Boolean)=viewModelScope.launch(Dispatchers.IO) {
        appRepository.allTasks.value?.let {
            var SaveDataModel =it.get(index)
            if(running){

                SaveDataModel.Running=running.toString()
                appRepository.Update(SaveDataModel)
            }else{
                var appSaveDataModel =AppSaveDataModel(SaveDataModel.Work,"0","1")
                appRepository.Insert(appSaveDataModel)
            }
        }
    }

}