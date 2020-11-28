package com.example.app01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.ActivityMainBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 0..5) {
            var newOne : itemAnnouncement = itemAnnouncement(i,i,"Announcement " + i,"Description Test " + i,false)
            dataObject.listAnnouncement.add(newOne)
        }
        for (i in 1..5) {
            var newOne : itemJob = itemJob("Job test " + i)
            newOne.pay = i * 1000
            dataObject.listJob.add(newOne)
        }
        for (i in 1..5) {
            var newOne : Worker = Worker("worker " + i)
            newOne.age = 30 + i
            newOne.id = i + 1
            dataObject.listWorker.add(newOne)
        }
        for (i in 1..5) {
            var newOne : Branch = Branch("Branch no." + i)
            newOne.numberOfWorker = i + 1
            dataObject.listBranch.add(newOne)
        }
        dataObject.selectBranch = dataObject.listBranch[0]
        dataObject.selectWorker = dataObject.listWorker[0]

        val binding  = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
    }
    fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
    fun getCurrentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
    fun getCurrentDay(): Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    fun getCurrentHour(): Int = Calendar.getInstance().get(Calendar.HOUR)
    fun getCurrentMinute(): Int = Calendar.getInstance().get(Calendar.MINUTE)
}