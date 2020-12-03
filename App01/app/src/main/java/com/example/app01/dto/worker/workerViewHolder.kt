package com.example.app01.dto.worker

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.databinding.ItemWorkerBinding

class workerViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemWorkerBinding = DataBindingUtil.bind(elementView)!!
    private var option : Int = 0
    private var work : Work = Work()
    private var payment : Int = 0

    fun setUsage(option : Int) {
        this.option = option
    }

    fun setWork(work : Work) {
        this.work = work
        this.payment = work.calculate()
    }

    fun bind(item: WorkerView, context: Context) {
        binding.value = item
        when (option) {
            1-> {
                // With wage visualization
                binding.staticAage = "Hourly wage"
                binding.wage = item.wage.toString()+"원"
            }
            2-> {
                binding.staticAage = "Daily payment"
                binding.wage = payment.toString()+"원"
            }
        }
    }
}