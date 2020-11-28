package com.example.app01.boss.history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentHistoryBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchAdapter
import com.example.app01.dto.worker.workerAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.dialog_branch_selection.view.*

class historyFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var mWorkerAdapter: workerAdapter
    private lateinit var mBranchAdapter : branchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)
        binding.currentBranch = dataObject.selectBranch.title
        binding.selectionBranch.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val view = inflater.inflate(R.layout.dialog_branch_selection, null)
            mBranchAdapter = branchAdapter()
            val listView = view.RvBranches
            listView.adapter = mBranchAdapter
            for (item : Branch in dataObject.listBranch) {
                mBranchAdapter.addItem(item)
            }
            dialog.setView(view)
            dialog.show()
            listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var select = parent?.getItemAtPosition(position) as Branch
                    binding.currentBranch = select.title
                    dataObject.selectBranch = select
                    dialog.cancel()
                }
            }
        }
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_managementFragment)
        }
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_scheduleFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_infoFragment)
        }
        mWorkerAdapter = workerAdapter(dataObject.listWorker, requireContext())
        binding.RvWorkers.adapter = mWorkerAdapter
        var snapHelper : PagerSnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RvWorkers)
        mWorkerAdapter.setItemClickListener(object : workerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "아직 기능 안붙임", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}