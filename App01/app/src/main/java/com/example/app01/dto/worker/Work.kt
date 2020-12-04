package com.example.app01.dto.worker

class Work {
    var id = 0
    var id_workerInfo: Int = 0
    var timeStart: String = ""
    var timeEnd: String= ""
    var payment : Int = 8590
    var hoursOfWork : String = "9:00"
    var dateWork : String = "2021-01-01"
    // 0 -> Attend | 1 -> Absence
    var attendence : Int = 1

    fun calculate() : Int {
        var spliter = timeEnd.split(":")
        var hourEnd = spliter[0].toInt()
        var minuteEnd = spliter[1].toInt()
        if (minuteEnd < 10) {
            timeEnd = hourEnd.toString() + ":0" + minuteEnd.toString()
        }
        spliter = timeStart.split(":")
        var hourStart = spliter[0].toInt()
        var minuteStart = spliter[1].toInt()
        var hourNormal = 0
        var minNormal = 0
        var hourNight = 0
        var minNight = 0
        if (hourEnd > 22 ) {
            if (hourStart > 22) {
                hourNight = hourEnd - hourStart
                hourNormal = hourEnd - hourStart
                minNight = minuteEnd - minuteStart
                minNormal = minuteEnd - minuteStart
                if (minNight < 0) {
                    hourNight -= 1
                    minNight += 60
                    hourNormal -= 1
                    minNormal += 60
                }
            } else {
                hourNight = hourEnd - 22
                hourNormal = hourEnd - hourStart - 1
                minNight = minuteEnd
                minNormal = 60 - minuteStart
            }
        } else {
            hourNight = 0
            hourNormal = hourEnd - hourStart
            minNight = 0
            minNormal = minuteEnd - minuteStart
            if (minNormal < 0) {
                hourNormal -= 1
                minNormal += 60
            }
        }
        if (minuteStart < 10) {
            timeStart = hourStart.toString() + ":0" + minuteStart.toString()
        }
        hoursOfWork = hourNormal.toString() + ":" + minNormal.toString()
        var totalPayment = (((hourNormal * 60) + minNormal) * payment + ((hourNight * 60) + minNight) * payment) / 60
        return totalPayment
    }

    fun getTime() : ArrayList<Int> {
        var spliter = timeEnd.split(":")
        var hourEnd = spliter[0].toInt()
        var minuteEnd = spliter[1].toInt()
        if (minuteEnd < 10) {
            timeEnd = hourEnd.toString() + ":0" + minuteEnd.toString()
        }
        spliter = timeStart.split(":")
        var hourStart = spliter[0].toInt()
        var minuteStart = spliter[1].toInt()
        var hourNormal = 0
        var minNormal = 0
        var hourNight = 0
        var minNight = 0
        if (hourEnd > 22 ) {
            if (hourStart > 22) {
                hourNight = hourEnd - hourStart
                hourNormal = hourEnd - hourStart
                minNight = minuteEnd - minuteStart
                minNormal = minuteEnd - minuteStart
                if (minNight < 0) {
                    hourNight -= 1
                    minNight += 60
                    hourNormal -= 1
                    minNormal += 60
                }
            } else {
                hourNight = hourEnd - 22
                hourNormal = hourEnd - hourStart - 1
                minNight = minuteEnd
                minNormal = 60 - minuteStart
            }
        } else {
            hourNight = 0
            hourNormal = hourEnd - hourStart
            minNight = 0
            minNormal = minuteEnd - minuteStart
            if (minNormal < 0) {
                hourNormal -= 1
                minNormal += 60
            }
        }
        return arrayListOf(hourNormal, minNormal, hourNight, minNight)
    }

    fun setFromWorkerInfo(workerInfo: WorkerInfo) {
        id_workerInfo = workerInfo.id
        timeStart = workerInfo.timeStart
        timeEnd = workerInfo.timeEnd
        payment = workerInfo.payment
    }
}