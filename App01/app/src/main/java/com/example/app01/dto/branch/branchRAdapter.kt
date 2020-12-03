package com.example.app01.dto.branch

import com.example.app01.R

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class branchRAdapter(
    var listBranch: ArrayList<Branch>, val context: Context, var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<branchViewHolder>() {

    interface ItemClickListener {
        fun onClick(view : View, position : Int)
        fun onLongClick(view : View, position : Int) : Boolean
    }

    fun setItems( newItems : ArrayList<Branch>) {
        listBranch = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): branchViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_branch, parent, false)

        return branchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBranch.size
    }

    override fun onBindViewHolder(holder: branchViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClickListener.onLongClick(it, position)
        }
        holder.bind(listBranch[position], context)
    }
}