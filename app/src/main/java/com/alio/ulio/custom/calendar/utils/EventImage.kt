package com.alio.ulio.custom.calendar.utils

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

sealed class EventImage {
    object EmptyEventImage : EventImage()
    data class EventImageResource(@DrawableRes val drawableRes: Int) : EventImage()
    data class EventImageDrawable(val drawable: Drawable) : EventImage()
}