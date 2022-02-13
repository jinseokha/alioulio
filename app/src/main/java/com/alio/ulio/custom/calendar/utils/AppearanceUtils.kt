package com.alio.ulio.custom.calendar.utils

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.alio.ulio.R
import kotlinx.android.synthetic.main.calendar_view.view.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-12
 * @desc
 */

internal fun View.setAbbreviationsLabels(color: Int, firstDayOfWeek: Int) {
    val labels = getAbbreviationsTextViews()

    val abbreviations = context.resources.getStringArray(R.array.material_calendar_day_abbreviations_array)

    labels.forEachIndexed { index, label ->
        label.text = abbreviations[(index + firstDayOfWeek - 1) % 7]

        if (color != 0) {
            label.setTextColor(color)
        }
    }
}

internal fun View.setAbbreviationsLabelsSize(size: Float) {
    val labels = getAbbreviationsTextViews()
    //val maxTextSize = resources.getDimensionPixelSize(R.dimen.text_size_max)
    val maxTextSize = 20
    labels.forEachIndexed { _, label ->
        if (size > 0.0 && size <= maxTextSize) {
            label.textSize = size
        }
    }
}

private fun View.getAbbreviationsTextViews() = listOf(
    mondayLabel,
    tuesdayLabel,
    wednesdayLabel,
    thursdayLabel,
    fridayLabel,
    saturdayLabel,
    sundayLabel)

internal fun View.setTypeface(typeface: Typeface?) {
    if (typeface == null) return
    getAbbreviationsTextViews().forEach { label ->
        label.typeface = typeface
    }
}

internal fun View.setHeaderColor(color: Int) {
    if (color == 0) return
    this.calendarHeader.setBackgroundColor(color)
}

internal fun View.setHeaderLabelColor(color: Int) {
    if (color == 0) return
    this.currentDateLabel.setTextColor(color)
}

internal fun View.setHeaderTypeface(typeface: Typeface?) {
    if (typeface == null) return
    this.currentDateLabel.typeface = typeface
}

internal fun View.setAbbreviationsBarColor(color: Int) {
    if (color == 0) return
    this.abbreviationsBar.setBackgroundColor(color)
}

internal fun View.setPagesColor(color: Int) {
    if (color == 0) return
    this.calendarViewPager.setBackgroundColor(color)
}

internal fun View.setPreviousButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    this.previousButton.setImageDrawable(drawable)
}

internal fun View.setForwardButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    this.forwardButton.setImageDrawable(drawable)
}

internal fun View.setHeaderVisibility(visibility: Int) {
    this.calendarHeader.visibility = visibility
}

internal fun View.setNavigationVisibility(visibility: Int) {
    this.previousButton.visibility = visibility
    this.forwardButton.visibility = visibility
}

internal fun View.setAbbreviationsBarVisibility(visibility: Int) {
    this.abbreviationsBar.visibility = visibility
}
