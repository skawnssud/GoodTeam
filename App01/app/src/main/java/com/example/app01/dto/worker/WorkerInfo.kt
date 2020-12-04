package com.example.app01.dto.worker

class WorkerInfo {
    var id = 0
    var id_worker : Int = 0
    var payment : Int = 8590
    var timeStart : String = "09:00"
    var timeEnd : String = "18:00"

    fun setWorkerInfo(worker : Worker) {
        id = worker.id_workerInfo
        timeStart = worker.timeStart
        timeEnd = worker.timeEnd
        payment = worker.payment
    }

}