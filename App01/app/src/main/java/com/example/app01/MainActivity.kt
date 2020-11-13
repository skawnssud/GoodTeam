package com.example.app01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.ActivityMainBinding


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
        val binding  = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
    }
}