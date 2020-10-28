package com.example.app01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        for (i in 0..5) {
            var newOne : itemAnnouncement = itemAnnouncement(i,i,"Announcement Test " + i,"Description Test " + i,false)
            dataObject.listAnnouncement.add(newOne)
        }
        val binding  = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
    }
}