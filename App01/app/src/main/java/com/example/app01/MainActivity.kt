package com.example.app01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..5) {
            var newOne : itemAnnouncement = itemAnnouncement(i,i,"Announcement Test " + i,"Description Test " + i,false)
            dataObject.listAnnouncement.add(newOne)
        }
        changeFragWorker(0)
    }

    fun changeFragWorker(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when (fragNum) {
            0 -> {
                ft.replace(R.id.frameMain, loginMain()).commit()
            }
            10 -> {
                ft.replace(R.id.frameMain, workerMainFrame()).commit()
            }
            11 -> {
                ft.replace(R.id.frameWorkerMain, workerSchedule()).commit()
            }
            21 -> {
                ft.replace(R.id.frameWorkerMain, workerCalculation()).commit()
            }
            31 -> {
                ft.replace(R.id.frameWorkerMain, workerJobHunting()).commit()
            }
            41 -> {
                ft.replace(R.id.frameWorkerMain, workerMypage()).commit()
            }

        }
    }
}