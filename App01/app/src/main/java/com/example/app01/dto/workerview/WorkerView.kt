package com.example.app01.dto.workerview

import com.example.app01.dto.worker.Worker

class WorkerView {
    // id_worker
    var id : Int = 0
    var name : String = ""
    var age : Int = 0
    var wage : Int = 0

    fun setWorkerView(worker : Worker) {
        name = worker.name
        age = worker.age
        wage = worker.payment
    }
}