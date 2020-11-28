package com.example.app01

import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker

object dataObject {
    var listAnnouncement : ArrayList<itemAnnouncement> = ArrayList<itemAnnouncement>()
    var listJob : ArrayList<itemJob> = ArrayList<itemJob>()
    var listWorker : ArrayList<Worker> = ArrayList<Worker>()
    var listBranch : ArrayList<Branch> = ArrayList<Branch>()
    var selectBranch : Branch = Branch("Initializing")
    var selectWorker: Worker = Worker("A")
}