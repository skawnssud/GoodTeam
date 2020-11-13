package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentWorkerMypageBinding

class workerMypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWorkerMypageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_mypage, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerMypageFragment_to_workerScheduleFragment)
        }
        binding.layoutCalculation.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerMypageFragment_to_workerCalculationFragment)
        }
        binding.layoutJobHunting.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerMypageFragment_to_workerJobhuntingFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}