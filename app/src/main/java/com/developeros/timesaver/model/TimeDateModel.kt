package com.developeros.timesaver.model

import java.util.*

class TimeDateModel() {
    val calender = Calendar.getInstance();
    val year = calender.get(Calendar.YEAR)
    var tempMonth:Int = calender.get(Calendar.MONTH)
    val mont = tempMonth+1
    val day = calender.get(Calendar.DAY_OF_MONTH)
    val hour = calender.get(Calendar.HOUR_OF_DAY)
    val minute = calender.get(Calendar.MINUTE)
    override fun toString(): String {
        return "TimeDateModel(year=$year, month=$mont, day=$day, hour=$hour, minute=$minute)"
    }
}