package com.example.app01.dto.worker

import kotlin.math.min

class Work {
    var id = 0
    var id_workerInfo: Int = 0
    var timeStart: String = ""
    var timeEnd: String= ""
    var payment : Int = 8590
    var hoursOfWork : String = "9:00"
    var dateWork : String = "2021-01-01"

    fun calculate() : Int {
        var spliter = timeEnd.split(":")
        var hour = spliter[0].toInt()
        var minute = spliter[1].toInt()
        if (minute < 10) {
            timeEnd = hour.toString() + ":0" + minute.toString()
        }
        spliter = timeStart.split(":")
        hour -= spliter[0].toInt()
        minute -= spliter[1].toInt()
        if (spliter[1].toInt() < 10) {
            timeStart = spliter[0] + ":0" + spliter[1]
        }
        if (minute < 0) {
            minute = 60 + minute
            hour = hour - 1
            hoursOfWork = hour.toString() + ":" + minute.toString()
        } else if (minute < 10) {
            hoursOfWork = hour.toString() + ":0" + minute.toString()
        } else {
            hoursOfWork = hour.toString() + ":" + minute.toString()
        }
        var totalPayment = ((hour * 60) + minute) * payment / 60
        return totalPayment
    }

    fun setFromWorkerInfo(workerInfo: WorkerInfo) {
        id_workerInfo = workerInfo.id
        timeStart = workerInfo.timeStart
        timeEnd = workerInfo.timeEnd
        payment = workerInfo.payment
    }
}