package com.alio.ulio.custom.calendar.listeners

import android.view.View
import android.widget.AdapterView
import com.alio.ulio.custom.calendar.EventDay
import com.alio.ulio.custom.calendar.utils.CalendarProperties
import com.alio.ulio.custom.calendar.utils.isBetweenMinAndMax
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

class DayRowLongClickListener(
    private val calendarProperties: CalendarProperties
) : AdapterView.OnItemLongClickListener {

    override fun onItemLongClick(adapterView: AdapterView<*>, view: View, position: Int, id: Long): Boolean {
        val day = GregorianCalendar().apply {
            time = adapterView.getItemAtPosition(position) as Date
        }

        calendarProperties.onDayClickListener?.let {
            onLongClick(day)
        }

        return true
    }

    private fun onLongClick(day: Calendar) {
        val eventDay = calendarProperties.eventDays.firstOrNull { it.calendar == day }
        callOnLongClickListener(eventDay ?: EventDay(day))
    }


    private fun callOnLongClickListener(eventDay: EventDay) {
        val enabledDay = calendarProperties.disabledDays.contains(eventDay.calendar)
                || !eventDay.calendar.isBetweenMinAndMax(calendarProperties)
        eventDay.isEnabled = enabledDay
        calendarProperties.onDayLongClickListener?.onDayLongClick(eventDay)
    }
}