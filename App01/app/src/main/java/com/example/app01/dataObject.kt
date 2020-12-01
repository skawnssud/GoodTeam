package com.example.app01

import com.example.app01.dto.Relation
import com.example.app01.dto.User
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker

object dataObject {
    var listAnnouncement : ArrayList<itemAnnouncement> = ArrayList<itemAnnouncement>()
    var listJob : ArrayList<itemJob> = ArrayList<itemJob>()
    var listWorker : ArrayList<Worker> = ArrayList<Worker>()
    var listBranch : ArrayList<Branch> = ArrayList<Branch>()
    var listRelation : ArrayList<Relation> = ArrayList()
    var selectUser : User = User()
    var selectBranch : Branch = Branch()
    var selectWorker: Worker = Worker()
    var selectRelation : Relation = Relation()
}