package com.example.app01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.worker_schedule.view.*

class workerSchedule : Fragment() {
    private lateinit var adapter : itemAnnouncementAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.worker_schedule, container, false)
        view.listAnnouncement.layoutManager = LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        adapter = itemAnnouncementAdapter(dataObject.listAnnouncement, requireContext())
        view.listAnnouncement.adapter = adapter
        var snapHelper : PagerSnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(view.listAnnouncement)
        return view
    }

}