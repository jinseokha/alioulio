package com.alio.ulio.custom.calendar.utils

import com.alio.ulio.custom.calendar.EventDay
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

internal fun Calendar.isEventDayWithLabelColor(calendarProperties: CalendarProperties): Boolean {
    return if (calendarProperties.eventsEnabled) {
        calendarProperties.eventDays.none { eventDate ->
            eventDate.calendar == this && eventDate.labelColor != 0
        }
    } else false
}

/**
 * This method is used to get event day which contains custom label color.
 *
 * @param this                A calendar instance representing day date
 * @param calendarProperties A calendar properties
 */
internal fun Calendar.getEventDayWithLabelColor(calendarProperties: CalendarProperties): EventDay? {
    return calendarProperties.eventDays.firstOrNull { eventDate ->
        eventDate.calendar == this && eventDate.labelColor != 0
    }
}
