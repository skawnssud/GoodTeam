package com.example.app01.boss

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentInfoBinding

class infoFragment : Fragment() {
    private lateinit var binding : FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_info, container, false)
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_managementFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_historyFragment)
        }
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_scheduleFragment)
        }
        var it = dataObject.selectUser
        binding.account = it.account
        binding.name = it.name
        binding.age = it.age.toString()
        if (dataObject.selectUser.role == 0) binding.role = "ADMIN/MANAGER" else binding.role = "WORKER"
        return binding.root
    }
}