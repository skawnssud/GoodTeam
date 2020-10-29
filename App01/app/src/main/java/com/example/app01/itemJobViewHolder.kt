package com.example.app01

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.worker_item_job.view.*

class itemJobViewHolder(elementView : View) : RecyclerView.ViewHolder(elementView) {
    var textTitle = elementView.textTitle
    var textPayment = elementView.textPay

    fun bind(itemJob: itemJob, context: Context) {
        textTitle.setText(itemJob.title)
        textPayment.setText(itemJob.pay.toString() + " 원")
    }
}