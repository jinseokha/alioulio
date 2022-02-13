package com.alio.ulio.custom.calendar.utils

import android.widget.ImageView

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

internal fun ImageView.loadImage(eventImage: EventImage) {
    when (eventImage) {
        is EventImage.EventImageDrawable -> setImageDrawable(eventImage.drawable)
        is EventImage.EventImageResource -> setImageResource(eventImage.drawableRes)
        is EventImage.EmptyEventImage -> Unit
    }
}
