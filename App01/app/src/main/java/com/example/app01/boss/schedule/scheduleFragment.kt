package com.example.app01.boss.schedule

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.DialogDateModificationBinding
import com.example.app01.databinding.FragmentScheduleBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchAdapter
import com.example.app01.dto.worker.Work
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.worker.WorkerInfo
import com.example.app01.dto.worker.workerAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.dialog_branch_selection.view.*
import java.util.*
import kotlin.collections.ArrayList

class scheduleFragment : Fragment() {
    private lateinit var binding: FragmentScheduleBinding
    private lateinit var mWorkerAdapter: workerAdapter
    private lateinit var mBranchAdapter: branchAdapter
    private var modifyOn: Boolean = false
    private var activateRangeListener : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_schedule, container, false
        )
        defaultSetting(0)

        // Dialog for Branch Selection
        binding.selectionBranch.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val view = inflater.inflate(R.layout.dialog_branch_selection, null)
            mBranchAdapter = branchAdapter()
            val listView = view.RvBranches
            listView.adapter = mBranchAdapter
            for (item: Branch in dataObject.listBranch) {
                mBranchAdapter.addItem(item)
            }
            dialog.setView(view)
            dialog.show()
            listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var select = parent?.getItemAtPosition(position) as Branch
                    binding.currentBranch = select.title
                    dataObject.selectBranch = select
                    (activity as MainActivity).getWorkerViewesByIdBranch(select.id)
                    mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
                    binding.RvWorkers.adapter = mWorkerAdapter
                    defaultSetting(0)
                    dialog.dismiss()
                }
            }
        }

        // Bottom navigation bar
        binding.layoutManagement.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_managementFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_historyFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_scheduleFragment_to_infoFragment)
        }

        // Worker Recyclerview
        mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
        binding.RvWorkers.adapter = mWorkerAdapter
        var snapHelper: PagerSnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.RvWorkers)

        // When Click Worker
        mWorkerAdapter.setItemClickListener(object : workerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (modifyOn == false) {
                    defaultSetting(position)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Modification is on process. please finish.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onLongClick(view: View, position: Int) : Boolean {
                return true
            }
        })

        // Time picker
        binding.textTimeStart.setOnClickListener {
            var timeListener = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    if (compareTime(p1.toString() + ":" + p2.toString(), binding.timeEnd.toString())) {
                        if (p2 < 10) {
                            binding.timeStart = p1.toString() + ":0" + p2.toString()
                        } else {
                            binding.timeStart = p1.toString() + ":" + p2.toString()
                        }
                        dataObject.selectWorker.timeStart = binding.timeStart.toString()
                        // Update WorkerInfo
                        var workerInfo : WorkerInfo = WorkerInfo()
                        workerInfo.setWorkerInfo(dataObject.selectWorker)
                        (activity as MainActivity).modifyWorkerInfo(workerInfo)
                        dataObject.selectWorker.infowork.values.forEach {
                            it.timeStart = dataObject.selectWorker.timeStart
                            it.calculate()
                            (activity as MainActivity).modifyWork(it)
                        }
                        dataObject.selectWorker.infowork = (activity as MainActivity).getWorksByIdWorkerInfo(dataObject.selectWorker.id_workerinfo)
                        dataObject.selectWorker.datesWork = dataObject.selectWorker.infowork.keys.toMutableList()
                    } else {
                        Toast.makeText(requireContext(), "Not possible. Starting time should be smaller than Ending time.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            var builder = TimePickerDialog(
                requireContext(),
                timeListener,
                (activity as MainActivity).getCurrentHour(),
                (activity as MainActivity).getCurrentMinute(),
                true
            )
            builder.show()

        }
        binding.textTimeEnd.setOnClickListener {
            var timeListener = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    if (compareTime(binding.timeStart.toString(), p1.toString() + ":" + p2.toString())) {
                        if (p2 < 10) {
                            binding.timeEnd = p1.toString() + ":0" + p2.toString()
                        } else {
                            binding.timeEnd = p1.toString() + ":" + p2.toString()
                        }
                        dataObject.selectWorker.timeEnd = binding.timeEnd.toString()
                        // Update WorkerInfo
                        var workerInfo : WorkerInfo = WorkerInfo()
                        workerInfo.setWorkerInfo(dataObject.selectWorker)
                        (activity as MainActivity).modifyWorkerInfo(workerInfo)
                        dataObject.selectWorker.infowork.values.forEach {
                            it.timeEnd = dataObject.selectWorker.timeEnd
                            it.calculate()
                            (activity as MainActivity).modifyWork(it)
                        }
                        dataObject.selectWorker.infowork = (activity as MainActivity).getWorksByIdWorkerInfo(dataObject.selectWorker.id_workerinfo)
                        dataObject.selectWorker.datesWork = dataObject.selectWorker.infowork.keys.toMutableList()
                    } else {
                        Toast.makeText(requireContext(), "Not possible. Starting time should be smaller than Ending time.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            var builder = TimePickerDialog(
                requireContext(),
                timeListener,
                (activity as MainActivity).getCurrentHour(),
                (activity as MainActivity).getCurrentMinute(),
                true
            )
            builder.show()
        }

        // Setting for Calender
        binding.Cv.state().edit()
            .setMinimumDate(
                CalendarDay.from(
                    (activity as MainActivity).getCurrentYear(),
                    (activity as MainActivity).getCurrentMonth(),
                    (activity as MainActivity).getCurrentDay()
                )
            )
            .commit()

        // Permit user select dates for each workers or not
        binding.buttonModify.setOnClickListener {
            if (!dataObject.selectWorker.name.equals("name")) {
                if (modifyOn == false) {
                    modifyOn = true
                    binding.Cv.selectionMode = MaterialCalendarView.SELECTION_MODE_RANGE
                    binding.buttonModify.setBackgroundColor(Color.RED)
                    paintCalander(dataObject.selectWorker.datesWork)
                } else {
                    modifyOn = false
                    binding.Cv.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE
                    binding.buttonModify.setBackgroundColor(Color.parseColor("#488bff"))
                    paintCalander(dataObject.selectWorker.datesWork)
                }
            }
        }

        // Save selected dates to target worker
        binding.Cv.setOnRangeSelectedListener { widget, dates ->
            if (activateRangeListener) {
                dataObject.selectWorker.datesWork.clear()
                dataObject.selectWorker.datesWork.addAll(dates)
                // Delete all works -> Create works
                (activity as MainActivity).deleteWorkByIdWorkInfo(dataObject.selectWorkerInfo.id)
                (activity as MainActivity).createWork(dataObject.selectWorkerInfo, dates)
                dataObject.selectWorker.infowork.clear()
            } else {

            }
            // 선택 날짜들 한땀한땀 집어넣기
            /**
            for (date in dates) {
                var newWork : Work = Work()
                newWork.id_worker = dataObject.selectWorker.id
                newWork.timeStart = dataObject.selectWorker.timeStart
                newWork.timeEnd = dataObject.selectWorker.timeEnd
                dataObject.selectWorker.infowork[date] = newWork
            }
            for (ind in 0..dataObject.listWorker.size - 1) {
                if (dataObject.listWorker[ind].id.equals(dataObject.selectWorker.id)) {
                    dataObject.listWorker[ind].datesWork.clear()
                    dataObject.listWorker[ind].datesWork.addAll(dates)
                    dataObject.listWorker[ind].infowork.clear()
                    for (date in dates) {
                        var newWork : Work = Work()
                        newWork.id_worker = dataObject.listWorker[ind].id
                        newWork.timeStart = dataObject.listWorker[ind].timeStart
                        newWork.timeEnd = dataObject.listWorker[ind].timeEnd
                        dataObject.listWorker[ind].infowork[date] = newWork
                    }
                }
            }
            */
        }

        // Dialog for each date available
        binding.Cv.setOnDateLongClickListener { widget, date ->
            if (dataObject.selectWorker.datesWork.contains(date)) {
                val dialog = AlertDialog.Builder(requireContext()).create()
                val bindingDialog : DialogDateModificationBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_date_modification, container, false)
                dialog.setView(bindingDialog.root)
                dialog.show()

                // Setting Default
                bindingDialog.date = date.month.toString() + "월 " + date.day.toString() + "일 " + date.year.toString()
                bindingDialog.timeStart = dataObject.selectWorker.infowork[date]!!.timeStart
                bindingDialog.timeEnd = dataObject.selectWorker.infowork[date]!!.timeEnd
                bindingDialog.payment = dataObject.selectWorker.infowork[date]!!.calculate().toString() + "원"
                bindingDialog.workhour = dataObject.selectWorker.infowork[date]!!.hoursOfWork

                // Time picker
                bindingDialog.textTimeStart.setOnClickListener {
                    var timeListener = object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                            // Check if it exceed timeEnd
                            if (compareTime(p1.toString() + ":" + p2.toString(), dataObject.selectWorker.infowork[date]!!.timeEnd)) {
                                if (p2 < 10) {
                                    bindingDialog.timeStart = p1.toString() + ":0" + p2.toString()
                                } else {
                                    bindingDialog.timeStart = p1.toString() + ":" + p2.toString()
                                }
                                dataObject.selectWorker.infowork[date]!!.timeStart = bindingDialog.timeStart.toString()
                                (activity as MainActivity).modifyWork(dataObject.selectWorker.infowork[date]!!)
                                bindingDialog.payment = dataObject.selectWorker.infowork[date]!!.calculate().toString() + "원"
                                bindingDialog.workhour = dataObject.selectWorker.infowork[date]!!.hoursOfWork
                            } else {
                                Toast.makeText(requireContext(), "Not possible. Starting time should be smaller than Ending time.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    var builder = TimePickerDialog(
                        requireContext(),
                        timeListener,
                        (activity as MainActivity).getCurrentHour(),
                        (activity as MainActivity).getCurrentMinute(),
                        true
                    )
                    builder.show()

                }
                bindingDialog.textTimeEnd.setOnClickListener {
                    var timeListener = object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                            if (compareTime(dataObject.selectWorker.infowork[date]!!.timeStart, p1.toString() + ":" + p2.toString())) {
                                if (p2 < 10) {
                                    bindingDialog.timeEnd = p1.toString() + ":0" + p2.toString()
                                } else {
                                    bindingDialog.timeEnd = p1.toString() + ":" + p2.toString()
                                }
                                dataObject.selectWorker.infowork[date]!!.timeEnd = bindingDialog.timeEnd.toString()
                                (activity as MainActivity).modifyWork(dataObject.selectWorker.infowork[date]!!)
                                bindingDialog.payment = dataObject.selectWorker.infowork[date]!!.calculate().toString() + "원"
                                bindingDialog.workhour = dataObject.selectWorker.infowork[date]!!.hoursOfWork
                            } else {
                                Toast.makeText(requireContext(), "Not possible. Starting time should be smaller than Ending time.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    var builder = TimePickerDialog(
                        requireContext(),
                        timeListener,
                        (activity as MainActivity).getCurrentHour(),
                        (activity as MainActivity).getCurrentMinute(),
                        true
                    )
                    builder.show()
                }
            }
        }

        return binding.root
    }

    fun defaultSetting(position : Int) {
        // Default Setting
        dataObject.selectWorker.setWorker(
            (activity as MainActivity)
                .getWorkerInfo(dataObject.listWorkerView[position].id, dataObject.selectBranch.id), dataObject.listWorkerView[position]
        )
        dataObject.selectWorkerInfo.setWorkerInfo(dataObject.selectWorker)
        dataObject.selectWorker.infowork = (activity as MainActivity).getWorksByIdWorkerInfo(dataObject.selectWorkerInfo.id)
        dataObject.selectWorker.datesWork = (activity as MainActivity).getWorksByIdWorkerInfo(dataObject.selectWorkerInfo.id).keys.toMutableList()
        binding.currentBranch = dataObject.selectBranch.title
        binding.currentWorker = dataObject.selectWorker.name
        binding.timeStart = dataObject.selectWorker.timeStart
        binding.timeEnd = dataObject.selectWorker.timeEnd
        paintCalander(dataObject.selectWorker.datesWork)
    }

    // Marking current selection dates on Calender
    fun paintCalander(dates: List<CalendarDay>) {
        binding.Cv.clearSelection()
        if (dates.size != 0) {
            activateRangeListener = false
            binding.Cv.selectRange(dates[0], dates[dates.size - 1])
            activateRangeListener = true
        }
    }

    // compare two hh:mm; True if A <= B
    fun compareTime(A : String, B : String) : Boolean {
        if (A.split(":")[0].toInt() == B.split(":")[0].toInt()) {
            return A.split(":")[1].toInt() <= B.split(":")[1].toInt()
        } else {
            return A.split(":")[0].toInt() <= B.split(":")[0].toInt()
        }
    }
}