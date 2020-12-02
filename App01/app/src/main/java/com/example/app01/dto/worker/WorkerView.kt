package com.example.app01.dto.worker

class WorkerView {
    // id_worker
    var id : Int = 0
    var name : String = ""
    var age : Int = 0

    fun setWorkerView(worker : Worker) {
        name = worker.name
        age = worker.age
    }
}