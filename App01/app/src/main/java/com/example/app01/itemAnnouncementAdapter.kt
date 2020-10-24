package com.example.app01

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class itemAnnouncementAdapter(
    var listAnnouncement : ArrayList<itemAnnouncement>, val context: Context
) : RecyclerView.Adapter<itemAnnouncementViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAnnouncementViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.worker_item_announcement, parent, false)

        return itemAnnouncementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAnnouncement.size
    }

    override fun onBindViewHolder(holder: itemAnnouncementViewHolder, position: Int) {
        holder.bind(listAnnouncement[position], context)
    }
}