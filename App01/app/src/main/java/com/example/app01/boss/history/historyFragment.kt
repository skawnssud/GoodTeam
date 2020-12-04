package com.example.app01.boss.history

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TableRow
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.custominterface.OnBackPressedListener
import com.example.app01.dataObject
import com.example.app01.databinding.DialogBranchSelectionBinding
import com.example.app01.databinding.FragmentHistoryBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchAdapter
import com.example.app01.dto.workerdetail.WorkerDetailAdapter
import com.example.app01.dto.workerview.workerViewAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay

class historyFragment : Fragment(), OnBackPressedListener {
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var mBranchAdapter : branchAdapter
    private lateinit var mWorkerDetailAdapter : WorkerDetailAdapter
    var mBackWait:Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)

        // Default Setting
        (activity as MainActivity).getWorkersAndDetails()
        binding.currentBranch = dataObject.selectBranch.title
        dataObject.listWorker = (activity as MainActivity).getWorkersByIdBranch(dataObject.selectBranch.id)

        // Recycler view for each worker's summary
        mWorkerDetailAdapter = WorkerDetailAdapter((activity as MainActivity), dataObject.listWorkerView, dataObject.listWorkerDetail, dataObject.listWorker, requireContext(), object : WorkerDetailAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
            }
            override fun onLongClick(view: View, position: Int): Boolean {
                return true
            }
        })
        binding.RvWorkerDetails.adapter = mWorkerDetailAdapter

        // Branch Selection
        binding.selectionBranch.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val bindingDialog : DialogBranchSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_branch_selection, container, false)
            mBranchAdapter = branchAdapter()
            val listView = bindingDialog.RvBranches
            listView.adapter = mBranchAdapter
            for (item : Branch in dataObject.listBranch) {
                mBranchAdapter.addItem(item)
            }
            dialog.setView(bindingDialog.root)
            dialog.show()
            listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var select = parent?.getItemAtPosition(position) as Branch
                    binding.currentBranch = select.title
                    dataObject.selectBranch = select
                    (activity as MainActivity).getWorkersAndDetails()
                    binding.table0to11.removeAllViewsInLayout()
                    binding.table12to23.removeAllViewsInLayout()
                    mWorkerDetailAdapter = WorkerDetailAdapter((activity as MainActivity), dataObject.listWorkerView, dataObject.listWorkerDetail, dataObject.listWorker, requireContext(), object : WorkerDetailAdapter.ItemClickListener {
                        override fun onClick(view: View, position: Int) {
                        }
                        override fun onLongClick(view: View, position: Int): Boolean {
                            return true
                        }
                    })
                    binding.RvWorkerDetails.adapter = mWorkerDetailAdapter
                    dialog.cancel()
                }
            }
        }

        // Bottom navigation bar
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_managementFragment)
        }
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_scheduleFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_infoFragment)
        }

        // When Select Calender
        binding.Cv.setOnDateChangedListener { widget, date, selected ->
            drawTimeTable(date)
        }

        return binding.root
    }

    // Create frame for time table
    fun createTimeTableFrame() {
        binding.table0to11.removeAllViewsInLayout()
        var tableRow : TableRow = TableRow(requireContext())
        tableRow.layoutParams = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val newText : TextView = TextView(requireContext())
        newText.width = 100
        newText.setBackgroundResource(R.drawable.back_03)
        newText.gravity = Gravity.CENTER
        newText.setText("name")
        tableRow.addView(newText)
        for (count in 0..11) {
            val newText : TextView = TextView(requireContext())
            newText.setBackgroundResource(R.drawable.back_03)
            newText.gravity = Gravity.CENTER
            newText.setText(count.toString())
            tableRow.addView(newText)
        }
        binding.table12to23.removeAllViewsInLayout()
        var tableRow2 : TableRow = TableRow(requireContext())
        tableRow2.layoutParams = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val newText2 : TextView = TextView(requireContext())
        newText2.width = 100
        newText2.setBackgroundResource(R.drawable.back_03)
        newText2.gravity = Gravity.CENTER
        newText2.setText("name")
        tableRow2.addView(newText2)
        for (count in 12..23) {
            val newText : TextView = TextView(requireContext())
            newText.gravity = Gravity.CENTER
            newText.setText(count.toString())
            tableRow2.addView(newText)
        }
        binding.table12to23.addView(tableRow2)
        binding.table0to11.addView(tableRow)
    }

    fun drawTimeTable(date : CalendarDay) {
        createTimeTableFrame()
        dataObject.listWorker.forEach {
            // Calculating blanks we have to paint
            var hourStart : Int = 0
            var minStart : Int = 0
            var hourEnd : Int = 0
            var minEnd : Int = 0
            if (it.datesWork.contains(date)) {
                hourStart = it.infowork[date]!!.timeStart.split(":")[0].toInt()
                minStart = it.infowork[date]!!.timeStart.split(":")[1].toInt()
                hourEnd = it.infowork[date]!!.timeEnd.split(":")[0].toInt()
                minEnd = it.infowork[date]!!.timeEnd.split(":")[1].toInt()
            }
            // Paint target blanks in timetable
            var tableRow : TableRow = TableRow(requireContext())
            tableRow.layoutParams = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val newText : TextView = TextView(requireContext())
            newText.setBackgroundResource(R.drawable.back_03)
            newText.gravity = Gravity.CENTER
            newText.setText(it.name)
            tableRow.addView(newText)
            for (count in 0..11) {
                val newText : TextView = TextView(requireContext())
                if (hourStart == 0 && hourEnd == 0) {
                } else {
                    if ((hourStart..hourEnd).contains(count)) {
                        newText.setBackgroundColor(Color.parseColor("#47ff8f"))
                    }
                }
                tableRow.addView(newText)
            }
            binding.table0to11.addView(tableRow)

            var tableRow2 : TableRow = TableRow(requireContext())
            tableRow2.layoutParams = TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val newText2 : TextView = TextView(requireContext())
            newText2.setBackgroundResource(R.drawable.back_03)
            newText2.gravity = Gravity.CENTER
            newText2.setText(it.name)
            tableRow2.addView(newText2)
            for (count in 12..23) {
                val newText : TextView = TextView(requireContext())
                if ((hourStart..hourEnd).contains(count)) {
                    newText.setBackgroundColor(Color.parseColor("#47ff8f"))
                }
                tableRow2.addView(newText)
            }
            binding.table12to23.addView(tableRow2)
        }
    }

    override fun onBackPressed(): Boolean {
        (activity as MainActivity).alertToast("Press back button one more if you want to terminate app.")
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
        } else {
            (activity as MainActivity).finish()
        }
        return true
    }
}