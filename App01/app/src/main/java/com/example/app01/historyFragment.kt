package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentHistoryBinding

class historyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_managementFragment)
        }
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_scheduleFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_infoFragment)
        }
        return binding.root
    }
}