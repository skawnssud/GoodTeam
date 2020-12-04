package com.example.app01.dto.workerdetail

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.R
import com.example.app01.databinding.ItemWorkerSpecificBinding
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.workerview.WorkerView
import com.prolificinteractive.materialcalendarview.CalendarDay

class WorkerDetailViewHolder(elementView : View)  : RecyclerView.ViewHolder(elementView) {
    private var binding : ItemWorkerSpecificBinding = DataBindingUtil.bind(elementView)!!
    private var isFulltime = false
    private var isNight = false

    fun bind(item: WorkerView, detail : WorkerDetail, worker : Worker, context: Context) {
        binding.value = item
        binding.wage = item.wage.toString()
        binding.Cv.setTopbarVisible(false)
        binding.Cv.isPagingEnabled = false
        binding.attendence = worker.datesWork.size.toString()
        binding.absence = "0"
        paintCalander(worker.datesWork)
        if (detail.fulltime == 1) isFulltime = true
        if (detail.night == 1) isNight = true
        if (isFulltime) binding.liveFulltime.setImageResource(R.drawable.dot_green)
        if (isNight) binding.liveNight.setImageResource(R.drawable.dot_green)
        binding.layoutFulltime.setOnClickListener {
            if (isFulltime) {
                binding.liveFulltime.setImageResource(R.drawable.dot_red)
                isFulltime = false
            } else {
                binding.liveFulltime.setImageResource(R.drawable.dot_green)
                isFulltime = true
            }
        }
        binding.layoutNight.setOnClickListener {
            if (isNight) {
                binding.liveNight.setImageResource(R.drawable.dot_red)
                isNight = false
            } else {
                binding.liveNight.setImageResource(R.drawable.dot_green)
                isNight = true
            }

        }
    }

    // Marking current selection dates on Calender
    fun paintCalander(dates: List<CalendarDay>) {
        binding.Cv.clearSelection()
        if (dates.size != 0) {
            binding.Cv.selectRange(dates[0], dates[dates.size - 1])
        }
    }
}