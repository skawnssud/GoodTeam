package com.example.app01.dto.workerdetail

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.selector.SelectRedDecorator
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.DialogBranchSelectionBinding
import com.example.app01.databinding.DialogCheckCommuteBinding
import com.example.app01.databinding.ItemWorkerSpecificBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchAdapter
import com.example.app01.dto.worker.Work
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.workerview.WorkerView
import com.example.app01.selector.SelectGreenDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class WorkerDetailViewHolder(elementView : View, parent: ViewGroup)  : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemWorkerSpecificBinding = DataBindingUtil.bind(elementView)!!
    private var isFulltime = false
    private var isNight = false
    private var parent : ViewGroup = parent

    fun bind(activity: MainActivity, item: WorkerView, detail : WorkerDetail, worker : Worker, position : Int, context: Context) {
        var timeTotal = getTimetotalByWeight(worker)
        var resultTotal = calculate(timeTotal, item.wage)
        binding.timeNormal = timeTotal[0].toString() + "h " + timeTotal[1].toString() + "m"
        binding.timeNight = timeTotal[2].toString() + "h " + timeTotal[3].toString() + "m"
        binding.timeFull = timeTotal[4].toString() + "h " + timeTotal[5].toString() + "m"
        binding.timeTotal = (timeTotal[0] + timeTotal[2] + timeTotal[4]).toString() + "h " + (timeTotal[1] + timeTotal[3] + timeTotal[5]).toString() + "m"
        binding.wageNormal = resultTotal[0].toString() + "원"
        binding.wageNight = resultTotal[1].toString() + "원"
        binding.wageFull = resultTotal[2].toString() + "원"
        binding.wageTotal = (resultTotal[0] + resultTotal[2] + resultTotal[1]).toString() + "원"
        binding.value = item
        binding.wage = item.wage.toString()
        binding.Cv.setTopbarVisible(false)
        binding.Cv.isPagingEnabled = false
        binding.attendence = worker.datesWork.size.toString()
        binding.absence = "0"
        paintCalander(activity, worker.infowork)
        if (detail.fulltime == 1) isFulltime = true
        if (detail.night == 1) isNight = true
        if (isFulltime) binding.liveFulltime.setImageResource(R.drawable.dot_green)
        if (isNight) binding.liveNight.setImageResource(R.drawable.dot_green)
        binding.layoutFulltime.setOnClickListener {
            if (isFulltime) {
                binding.liveFulltime.setImageResource(R.drawable.dot_red)
                detail.fulltime = 0
                dataObject.listWorkerDetail[position].fulltime = 0
                activity.modifyWorkerDetail(dataObject.listWorkerDetail[position])
                isFulltime = false
            } else {
                binding.liveFulltime.setImageResource(R.drawable.dot_green)
                detail.fulltime = 1
                dataObject.listWorkerDetail[position].fulltime = 1
                activity.modifyWorkerDetail(dataObject.listWorkerDetail[position])
                isFulltime = true
            }

        }
        binding.layoutNight.setOnClickListener {
            if (isNight) {
                binding.liveNight.setImageResource(R.drawable.dot_red)
                detail.night = 0
                dataObject.listWorkerDetail[position].night = 0
                activity.modifyWorkerDetail(dataObject.listWorkerDetail[position])
                isNight = false
            } else {
                binding.liveNight.setImageResource(R.drawable.dot_green)
                detail.night = 1
                dataObject.listWorkerDetail[position].night = 1
                activity.modifyWorkerDetail(dataObject.listWorkerDetail[position])
                isNight = true
            }

        }
        binding.Cv.setOnDateLongClickListener { widget, date ->
            if (worker.datesWork.contains(date)) {
                val dialog = AlertDialog.Builder(context).create()
                val bindingDialog : DialogCheckCommuteBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_check_commute, parent, false)
                dialog.setView(bindingDialog.root)
                dialog.show()
                bindingDialog.buttonAbsence.setOnClickListener {
                    worker.infowork[date]!!.attendence = 1
                    activity.modifyWork(worker.infowork[date]!!)
                    paintDateAbsence(activity, date)
                    dialog.dismiss()
                }
                bindingDialog.buttonCommute.setOnClickListener {
                    worker.infowork[date]!!.attendence = 0
                    activity.modifyWork(worker.infowork[date]!!)
                    paintDateAttend(activity, date)
                    dialog.dismiss()
                }
            }
        }
    }

    fun paintDateAttend(activity: MainActivity, date : CalendarDay) {
        binding.Cv.addDecorators(
            SelectGreenDecorator(
                activity,
                date
            )
        )
    }
    fun paintDateAbsence(activity: MainActivity, date : CalendarDay) {
        binding.Cv.addDecorators(
            SelectRedDecorator(
                activity,
                date
            )
        )
    }
    // Marking current selection dates on Calender
    fun paintCalander(activity: MainActivity, works: HashMap<CalendarDay, Work>) {
        binding.Cv.clearSelection()
        works.forEach { calendarDay, work ->
            if (work.attendence == 0) {
                paintDateAttend(activity, calendarDay)
            } else {
                paintDateAbsence(activity, calendarDay)
            }
        }
    }

    fun getTimetotalByWeight(worker : Worker) : ArrayList<Int> {
        var cal = Calendar.getInstance()
        var listTimeTotal = ArrayList<ArrayList<Int>>()
        var hourTotalNormal = 0
        var minTotalNormal= 0
        var hourTotalNight = 0
        var minTotalNight = 0
        for (i in 0..worker.infowork.size - 1) {
            var calendarDay = worker.datesWork[i]
            var work = worker.infowork[calendarDay]
            cal.set(calendarDay.year, calendarDay.month, calendarDay.day)
            if (cal.get(Calendar.DAY_OF_WEEK) != 7 && i != worker.infowork.size - 1) {
                var k = work!!.getTime()
                hourTotalNormal += k[0]
                minTotalNormal += k[1]
                hourTotalNight += k[2]
                minTotalNight += k[3]
            } else {
                if (minTotalNormal > 60) {
                    hourTotalNormal += minTotalNormal / 60
                    minTotalNormal %= 60
                }
                if (minTotalNight > 60) {
                    hourTotalNight += minTotalNight / 60
                    minTotalNight %= 60
                }
                listTimeTotal.add(arrayListOf(hourTotalNormal, minTotalNormal, hourTotalNight, minTotalNight))
                hourTotalNormal = 0
                minTotalNormal= 0
                hourTotalNight = 0
                minTotalNight = 0
            }
        }
        var result = arrayListOf(0, 0, 0, 0, 0, 0)
        for (i in 0..listTimeTotal.size - 1) {
            result[0] += listTimeTotal[i][0]
            result[1] += listTimeTotal[i][1]
            result[2] += listTimeTotal[i][2]
            result[3] += listTimeTotal[i][3]
            if (listTimeTotal[i][0] >= 15) {
                result[4] += listTimeTotal[i][0] - 15
                result[5] += listTimeTotal[i][1]
            } else {
                result[4] += 0
                result[5] += 0
            }
        }
        return result
    }

    fun calculate(it: ArrayList<Int>, wage: Int): ArrayList<Int> {
        var wageNormal = 0
        var wageNight = 0
        var wageFulltime = 0
        wageNormal += (it[0] * 60 + it[1]) * wage / 60
        wageNight += (it[2] * 60 + it[3]) * wage / 60
        wageFulltime += (it[4] * 60 + it[5]) * wage / 60
        return arrayListOf(wageNormal, wageNight, wageFulltime)
    }
}