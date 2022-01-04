package com.developeros.timesaver.model

import android.provider.Settings.Global.getString
import com.developeros.timesaver.R

class TimeManipulation {
    var TimeinFormat=""
    public fun DateTimeToSecond(year: String,month: String,day: String,hour: String,minute: String):Long{
        var Year = year.toLong()
        var Month = month.toLong()
        var Day = day.toLong()
        var Hour = hour.toLong()
        var Minute = minute.toLong()
        var seconds : Long =0
        seconds = Minute*60
        seconds= seconds + Hour*3600
        seconds= seconds + Day*86400
        seconds= seconds + Month*86400*30
        seconds= seconds + Year*86400*30*12
        return seconds
    }
    fun SecondToTimeFormated(second:String):String{
        var time : Long =second.toLong()
        var hour = time/3600
        var minutes = (time%3600)/60
        var seconds = time%60
         TimeinFormat  = String.format("%02d:%02d:%02d", hour, minutes, seconds)
        return TimeinFormat
    }

}