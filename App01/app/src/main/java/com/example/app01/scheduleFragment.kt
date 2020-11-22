package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentScheduleBinding

class scheduleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentScheduleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_managementFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_historyFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_infoFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}