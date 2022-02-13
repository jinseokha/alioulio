package com.alio.ulio.custom.calendar

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import com.alio.ulio.custom.calendar.utils.EventImage
import com.alio.ulio.custom.calendar.utils.setMidnight
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

data class EventDay(val calendar: Calendar) {
    //An object which contains image to display in the day row
    internal var imageDrawable: EventImage = EventImage.EmptyEventImage

    internal var labelColor: Int = 0

    @set:RestrictTo(RestrictTo.Scope.LIBRARY)
    var isEnabled: Boolean = false

    init {
        calendar.setMidnight()
    }

    constructor(day: Calendar, drawable: Drawable) : this(day) {
        imageDrawable = EventImage.EventImageDrawable(drawable)
    }

    constructor(day: Calendar, @DrawableRes drawableRes: Int) : this(day) {
        imageDrawable = EventImage.EventImageResource(drawableRes)
    }

    constructor(day: Calendar, @DrawableRes drawableRes: Int, @ColorInt labelColor: Int) : this(day) {
        imageDrawable = EventImage.EventImageResource(drawableRes)
        this.labelColor = labelColor
    }
}