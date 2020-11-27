package com.example.app01.dto.branch

import android.content.Context
import com.example.app01.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.app01.databinding.ItemBranchBinding

class branchAdapter() : BaseAdapter() {
    private var listBranch = ArrayList<Branch>()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val inflater = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<ItemBranchBinding>(inflater, R.layout.item_branch, parent, false)
        binding.value = listBranch[position]
        return binding.root
    }

    override fun getItem(position: Int): Any {
        return listBranch[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listBranch.size
    }

    fun addItem(item : Branch) {
        listBranch.add(item)
    }

}