package com.example.app01.dto.worker

import com.prolificinteractive.materialcalendarview.CalendarDay

data class Worker (var nam : String) {
    var id : Int = 0
    var name : String = nam
    var age : Int = 30
    var live : Boolean = false
    var datesWork :  MutableList<CalendarDay> = mutableListOf<CalendarDay>()
}
