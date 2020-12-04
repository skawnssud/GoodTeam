package com.example.app01.worker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentWorkerMypageBinding

class workerMypageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWorkerMypageBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_worker_mypage, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerMypageFragment_to_workerScheduleFragment)
        }
        var it = dataObject.selectUser
        binding.account = it.account
        binding.name = it.name
        binding.age = it.age.toString()
        if (dataObject.selectUser.role == 0) binding.role = "ADMIN/MANAGER" else binding.role = "WORKER"
        // Inflate the layout for this fragment
        return binding.root
    }

}