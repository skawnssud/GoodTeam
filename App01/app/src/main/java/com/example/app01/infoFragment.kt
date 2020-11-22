package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentInfoBinding

class infoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_managementFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_historyFragment)
        }
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_scheduleFragment)
        }
        return binding.root
    }
}