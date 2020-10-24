package com.example.app01

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.worker_main_frame.*
import kotlinx.android.synthetic.main.worker_main_frame.view.*

class workerMainFrame : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.worker_main_frame, container, false)
        (activity as MainActivity).changeFragWorker(11)
        view.imageSchedule.setColorFilter(Color.parseColor("#0066ff"))
        view.textSchedule.setTextColor(Color.parseColor("#0066ff"))
        view.layoutSchedule.setOnClickListener { clickMenu(it.id) }
        view.layoutCalculation.setOnClickListener { clickMenu(it.id) }
        view.layoutJobHunting.setOnClickListener { clickMenu(it.id) }
        view.layoutMyPage.setOnClickListener { clickMenu(it.id) }
        return view
    }

    fun clickMenu(id : Int) {
        setAllUnclick()
        when (id) {
            R.id.layoutSchedule -> {
                imageSchedule.setColorFilter(Color.parseColor("#0066ff"))
                textSchedule.setTextColor(Color.parseColor("#0066ff"))
                (activity as MainActivity).changeFragWorker(11)
            }
            R.id.layoutCalculation -> {
                imageCalculation.setColorFilter(Color.parseColor("#0066ff"))
                textCalculation.setTextColor(Color.parseColor("#0066ff"))
                (activity as MainActivity).changeFragWorker(21)
            }
            R.id.layoutJobHunting -> {
                imageJobHunting.setColorFilter(Color.parseColor("#0066ff"))
                textJobHunting.setTextColor(Color.parseColor("#0066ff"))
                (activity as MainActivity).changeFragWorker(31)
            }
            R.id.layoutMyPage -> {
                imageMyPage.setColorFilter(Color.parseColor("#0066ff"))
                textMyPage.setTextColor(Color.parseColor("#0066ff"))
                (activity as MainActivity).changeFragWorker(41)
            }
        }
    }

    fun setAllUnclick() {
        imageSchedule.setColorFilter(Color.parseColor("#AAAAAA"))
        textSchedule.setTextColor(Color.parseColor("#AAAAAA"))
        imageCalculation.setColorFilter(Color.parseColor("#AAAAAA"))
        textCalculation.setTextColor(Color.parseColor("#AAAAAA"))
        imageJobHunting.setColorFilter(Color.parseColor("#AAAAAA"))
        textJobHunting.setTextColor(Color.parseColor("#AAAAAA"))
        imageMyPage.setColorFilter(Color.parseColor("#AAAAAA"))
        textMyPage.setTextColor(Color.parseColor("#AAAAAA"))
    }

}