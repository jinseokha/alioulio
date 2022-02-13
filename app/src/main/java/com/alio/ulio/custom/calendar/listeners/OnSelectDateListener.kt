package com.alio.ulio.custom.calendar.listeners

import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

interface OnSelectDateListener {

    @JvmSuppressWildcards
    fun onSelect(calendar: List<Calendar>)
}