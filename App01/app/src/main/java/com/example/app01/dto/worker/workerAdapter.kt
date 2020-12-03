package com.example.app01.dto.worker

import com.example.app01.R

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class workerAdapter(
    var listWorker: ArrayList<WorkerView>, val context: Context, var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<workerViewHolder>() {

    private var option : Int = 0
    private var listWork : ArrayList<Work> = ArrayList<Work>()

    interface ItemClickListener {
        fun onClick(view : View, position : Int)
        fun onLongClick(view : View, position : Int) : Boolean
    }

    fun setOption(option : Int) {
        this.option = option
    }
    fun setListWork(work : ArrayList<Work>) {
        this.listWork = work
    }

    fun setItems(newItems : ArrayList<WorkerView>) {
        listWorker = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): workerViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false)

        return workerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWorker.size
    }

    override fun onBindViewHolder(holder: workerViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener!!.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClickListener!!.onLongClick(it, position)
        }
        when (option) {
            1 -> {
                holder.setUsage(option)
            }
            2 -> {
                holder.setUsage(option)
                holder.setWork(listWork[position])
            }
        }
        holder.bind(listWorker[position], context)
    }
}