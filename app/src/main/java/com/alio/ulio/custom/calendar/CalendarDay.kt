package com.alio.ulio.custom.calendar

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

class CalendarDay(val calendar: Calendar) {
    @ColorRes
    var labelColor: Int? = null

    @DrawableRes
    var backgroundResource: Int? = null

    var backgroundDrawable: Drawable? = null

    @ColorRes
    var selectedLabelColor: Int? = null

    @DrawableRes
    var selectedBackgroundResource: Int? = null

    var selectedBackgroundDrawable: Drawable? = null
}