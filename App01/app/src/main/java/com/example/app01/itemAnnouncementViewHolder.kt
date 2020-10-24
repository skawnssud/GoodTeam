package com.example.app01

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.worker_item_announcement.view.*

class itemAnnouncementViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    var textTitle = elementView.textTitle
    var textDesc = elementView.textDesc

    fun bind(itemAnnouncement: itemAnnouncement, context: Context) {
        textTitle.setText(itemAnnouncement.title)
        textDesc.setText(itemAnnouncement.desc)
    }
}