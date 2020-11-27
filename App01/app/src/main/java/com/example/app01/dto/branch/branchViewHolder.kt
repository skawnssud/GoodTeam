package com.example.app01.dto.branch

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.databinding.ItemBranchBinding

class branchViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemBranchBinding = DataBindingUtil.bind(elementView)!!

    fun bind(item: Branch, context: Context) {
        binding.value = item
    }
}