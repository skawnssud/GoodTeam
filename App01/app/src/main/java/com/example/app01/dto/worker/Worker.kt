package com.example.app01.dto.worker

import com.prolificinteractive.materialcalendarview.CalendarDay

data class Worker (var nam : String) {
    var id : Int = 0
    var name : String = nam
    var age : Int = 30
    var live : Boolean = false
    var datesWork :  MutableList<CalendarDay> = mutableListOf<CalendarDay>()
    var infowork : HashMap<CalendarDay, Work> = hashMapOf<CalendarDay, Work>()
    var timeStart: String = "9:00"
    var timeEnd : String = "18:00"
    var payment : Int = 8590
}
