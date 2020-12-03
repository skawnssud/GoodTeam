package com.example.app01.dto.workerview

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.databinding.ItemWorkerBinding

class workerViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemWorkerBinding = DataBindingUtil.bind(elementView)!!
    private var option : Int = 0

    fun setUsage(option : Int) {
        this.option = option
    }

    fun bind(item: WorkerView, context: Context) {
        binding.value = item
        when (option) {
            1-> {
                // With wage visualization
                binding.staticAage = "Hourly wage"
                binding.wage = item.wage.toString()+"원"
            }
        }
    }
}