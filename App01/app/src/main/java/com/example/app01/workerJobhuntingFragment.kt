package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentWorkerJobhuntingBinding

class workerJobhuntingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWorkerJobhuntingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_jobhunting, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerJobhuntingFragment_to_workerScheduleFragment)
        }
        binding.layoutCalculation.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerJobhuntingFragment_to_workerCalculationFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerJobhuntingFragment_to_workerMypageFragment)
        }

        return binding.root
    }

}