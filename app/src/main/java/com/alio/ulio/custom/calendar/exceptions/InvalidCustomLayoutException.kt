package com.alio.ulio.custom.calendar.exceptions

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

object InvalidCustomLayoutException : RuntimeException("YOUR CUSTOM CALENDAR DAY LAYOUT MUST CONTAIN A TextView WITH android:id=\"@+id/dayLabel\"")