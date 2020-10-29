package com.example.app01

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class itemJobAdapter(
    var listJob : ArrayList<itemJob>, val context: Context
) : RecyclerView.Adapter<itemJobViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemJobViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.worker_item_job, parent, false)

        return itemJobViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listJob.size
    }

    override fun onBindViewHolder(holder: itemJobViewHolder, position: Int) {
        holder.bind(listJob[position], context)
    }
}