package com.alio.ulio.view.ui.main.alarmsend.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.R
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.db.entity.Alarm
import com.alio.ulio.util.Event
import com.alio.ulio.view.dialog.DialogAccessDeniedDialog
import com.alio.ulio.view.ui.sign.SignActivity


/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-31
 * @desc
 */

class AlarmConditionViewModel(application: Application) : BaseViewModel(application){

    val calendarVisible = ObservableField<Boolean>(false)
    val text_date : ObservableField<String> = ObservableField<String>()

    val hour : ObservableField<String> = ObservableField<String>()
    val minute : ObservableField<String> = ObservableField<String>()

    val amClick : ObservableField<Boolean> = ObservableField<Boolean>(true)
    val pmClick : ObservableField<Boolean> = ObservableField<Boolean>(false)

    val nextEnable : ObservableField<Boolean> = ObservableField<Boolean>(false)

    private val _nextEvent = MutableLiveData<Event<Alarm>>()
    val nextEvent : LiveData<Event<Alarm>>
        get() = _nextEvent

    fun onDateChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        text_date.set(s.toString())
        var isValidDate = !TextUtils.isEmpty(text_date.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidDate && isValidSelected && isValidHour && isValidMinute
        validation(isValid)
    }

    fun onHourChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        hour.set(s.toString())
        var isValidDate = !TextUtils.isEmpty(text_date.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())

        var isValid = isValidDate && isValidSelected && isValidHour
        validation(isValid)
    }

    fun onMinuteChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        minute.set(s.toString())
        var isValidDate = !TextUtils.isEmpty(text_date.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidDate && isValidSelected && isValidHour
        validation(isValid)
    }

    fun validation(valid : Boolean) {
        nextEnable.set(valid)
    }

    fun onNextEvent() {
        if (minute.get() == null)
            minute.set("00")
        _nextEvent.value = Event(Alarm(hour.get(), minute.get()))
    }

    fun onCalendarClick() {
        val temp = calendarVisible.get() ?: true

        calendarVisible.set(!temp)
    }

}