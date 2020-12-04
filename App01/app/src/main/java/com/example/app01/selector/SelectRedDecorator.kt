package com.example.app01.selector

import android.R
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class SelectRedDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
    var myDay = currentDay
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
        view.setSelectionDrawable(ColorDrawable(Color.parseColor("#fd5865")))
    }

}