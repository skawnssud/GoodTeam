package com.example.app01.dto.worker

class Work {
    var id = 0
    var id_workerinfo: Int = 0
    var timeStart: String = ""
    var timeEnd: String= ""
    var payment : Int = 8590
    var hoursOfWork : String = "9:00"
    var dateWork : String = "2021-01-01"

    fun calculate() : Int {
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
        var totalPayment = ((hour * 60) + minute) * payment / 60
        return totalPayment
    }

    fun setFromWorkerInfo(workerInfo: WorkerInfo) {
        id_workerinfo = workerInfo.id
        timeStart = workerInfo.timeStart
        timeEnd = workerInfo.timeEnd
        payment = workerInfo.payment
    }
}