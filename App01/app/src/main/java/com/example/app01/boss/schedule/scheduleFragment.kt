package com.example.app01.boss.schedule

import android.app.AlertDialog
import android.graphics.Color
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
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentScheduleBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchAdapter
import com.example.app01.dto.worker.workerAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.dialog_branch_selection.view.*
import java.util.*
import kotlin.collections.ArrayList

class scheduleFragment : Fragment() {
    private lateinit var binding : FragmentScheduleBinding
    private lateinit var mWorkerAdapter: workerAdapter
    private lateinit var mBranchAdapter : branchAdapter
    private var modifyOn : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_schedule, container, false)
        binding.currentBranch = dataObject.selectBranch.title
        binding.currentWorker = dataObject.selectWorker.name
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
                    dialog.dismiss()
                }
            }
        }
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_managementFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_historyFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_infoFragment)
        }
        mWorkerAdapter = workerAdapter(dataObject.listWorker, requireContext())
        binding.RvWorkers.adapter = mWorkerAdapter
        var snapHelper : PagerSnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RvWorkers)
        mWorkerAdapter.setItemClickListener(object : workerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (modifyOn == false) {
                    dataObject.selectWorker = dataObject.listWorker[position]
                    binding.currentWorker = dataObject.selectWorker.name
                    paintCalander(dataObject.selectWorker.datesWork)
                } else {
                    Toast.makeText(requireContext(), "Modification is on process. please finish.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Setting for Calender
        binding.Cv.state().edit()
            .setMinimumDate(
                CalendarDay.from(
                    (activity as MainActivity).getCurrentYear(),
                    (activity as MainActivity).getCurrentMonth(),
                    (activity as MainActivity).getCurrentDay() + 1
                )
            )
            .commit()
        // Permit user select dates for each workers or not
        binding.buttonModify.setOnClickListener {
            if (!dataObject.selectWorker.name.equals("A")) {
                if (modifyOn == false) {
                    modifyOn = true
                    binding.Cv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE)
                    binding.buttonModify.setBackgroundColor(Color.RED)
                    paintCalander(dataObject.selectWorker.datesWork)
                }
                else {
                    modifyOn = false
                    binding.Cv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE)
                    binding.buttonModify.setBackgroundColor(Color.parseColor("#488bff"))
                    paintCalander(dataObject.selectWorker.datesWork)
                }
            }
        }
        // Save selected dates to target worker
        binding.Cv.setOnRangeSelectedListener { widget, dates ->
            dataObject.selectWorker.datesWork.clear()
            dataObject.selectWorker.datesWork.addAll(dates)
            for (ind in 0..dataObject.listWorker.size - 1) {
                if (dataObject.listWorker[ind].id.equals(dataObject.selectWorker.id)) {
                    dataObject.listWorker[ind].datesWork.clear()
                    dataObject.listWorker[ind].datesWork.addAll(dates)
                }
            }
        }

        return binding.root
    }
    fun paintCalander(dates : List<CalendarDay>) {
        binding.Cv.clearSelection()
        if (dates.size != 0) {
            binding.Cv.selectRange(dates[0], dates[dates.size-1])
        }
    }
}