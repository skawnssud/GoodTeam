package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.app01.databinding.FragmentWorkerScheduleBinding

class workerScheduleFragment : Fragment() {
    private lateinit var adapter : itemAnnouncementAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentWorkerScheduleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_schedule, container, false)
        binding.layoutCalculation.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerScheduleFragment_to_workerCalculationFragment)
        }
        binding.layoutJobHunting.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerScheduleFragment_to_workerJobhuntingFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_workerScheduleFragment_to_workerMypageFragment)
        }
        adapter = itemAnnouncementAdapter(dataObject.listAnnouncement, requireContext())
        var snapHelper : PagerSnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.listAnnouncement)
        return binding.root
    }
}