package com.developeros.timesaver.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "time_saver_table")
class AppSaveDataModel(){
    @PrimaryKey(autoGenerate = true)
    private var id:Int=0
    var Year=""
    var Month=""
    var Day=""
    var Hour=""
    var Minute=""
    var Running=""
    var Work=""
    var totalWork=""
    var Category=""
    constructor(work:String,total_work:String,category:String) : this() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        var tempMonth:Int = calender.get(Calendar.MONTH)
        val mont = tempMonth+1
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)
        Year=year.toString()
        Month=mont.toString()
        Day=day.toString()
        Hour=hour.toString()
        Minute=minute.toString()
        Running=false.toString()
        Work=work
        totalWork=total_work
        Category=category
    }
    fun setId(id:Int){
        this.id=id
    }
    fun getId():Int{
        return id
    }
}