package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentManagementBinding

class managementFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentManagementBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_management, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_scheduleFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_historyFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_infoFragment)
        }
        return binding.root
    }

}