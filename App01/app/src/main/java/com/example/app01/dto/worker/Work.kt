package com.example.app01.dto.worker

class Work {
    var id_worker: Int = 0
    var timeStart: String = ""
    var timeEnd: String= ""
    var payment : Int = 8590
    var hoursOfWork : String = "9:00"
    fun calculate(fee : Int) {
        var spliter = timeEnd.split(":")
        var hour = spliter[0].toInt()
        var minute = spliter[1].toInt()
        spliter = timeStart.split(":")
        hour -= spliter[0].toInt()
        minute -= spliter[1].toInt()
        if (minute < 10) {
            hoursOfWork = hour.toString() + ":0" + minute.toString()
        } else {
            hoursOfWork = hour.toString() + ":" + minute.toString()
        }
        payment = ((hour * 60) + minute) * fee / 60
    }
}