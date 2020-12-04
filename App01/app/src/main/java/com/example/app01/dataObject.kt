package com.example.app01

import com.example.app01.dto.workerview.WorkerView
import com.example.app01.dto.User
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.worker.WorkerInfo
import com.example.app01.dto.workerdetail.WorkerDetail

object dataObject {
    var listAnnouncement : ArrayList<itemAnnouncement> = ArrayList<itemAnnouncement>()
    var listJob : ArrayList<itemJob> = ArrayList<itemJob>()
    var listWorker : ArrayList<Worker> = ArrayList<Worker>()
    var listBranch : ArrayList<Branch> = ArrayList<Branch>()
    var listWorkerView : ArrayList<WorkerView> = ArrayList()
    var listWorkerDetail : ArrayList<WorkerDetail> = ArrayList()
    var selectUser : User = User()
    var selectBranch : Branch = Branch()
    var selectWorker: Worker = Worker()
    var selectWorkerInfo : WorkerInfo = WorkerInfo()
}
