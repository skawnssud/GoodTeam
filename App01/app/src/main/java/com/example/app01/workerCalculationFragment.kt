package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentWorkerCalculationBinding

class workerCalculationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWorkerCalculationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_calculation, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerCalculationFragment_to_workerScheduleFragment)
        }
        binding.layoutJobHunting.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerCalculationFragment_to_workerJobhuntingFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerCalculationFragment_to_workerMypageFragment)
        }
        return binding.root
    }

}