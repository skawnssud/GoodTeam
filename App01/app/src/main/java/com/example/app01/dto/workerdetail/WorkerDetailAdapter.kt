package com.example.app01.dto.workerdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.R
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.workerview.WorkerView

class WorkerDetailAdapter(
    var listWorkerView: ArrayList<WorkerView>, var listWorkerDetail: ArrayList<WorkerDetail>, var listWorker: ArrayList<Worker>, val context: Context, var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<WorkerDetailViewHolder>() {

    interface ItemClickListener {
        fun onClick(view : View, position : Int)
        fun onLongClick(view : View, position : Int) : Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerDetailViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_worker_specific, parent, false)

        return WorkerDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWorker.size
    }

    override fun onBindViewHolder(holder: WorkerDetailViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClickListener!!.onLongClick(it, position)
        }
        holder.bind(listWorkerView[position], listWorkerDetail[position], listWorker[position], context)
    }
}