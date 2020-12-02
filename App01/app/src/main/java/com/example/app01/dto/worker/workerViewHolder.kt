package com.example.app01.dto.worker

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.databinding.ItemWorkerBinding

class workerViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemWorkerBinding = DataBindingUtil.bind(elementView)!!

    fun bind(item: WorkerView, context: Context) {
        binding.value = item
    }
}