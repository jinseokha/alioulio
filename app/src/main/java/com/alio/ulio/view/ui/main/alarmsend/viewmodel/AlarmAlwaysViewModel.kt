package com.alio.ulio.view.ui.main.alarmsend.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.db.entity.Alarm
import com.alio.ulio.util.Event

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-23
 * @desc
 */
class AlarmAlwaysViewModel(application : Application) : BaseViewModel(application) {

    val text_title : ObservableField<String> = ObservableField<String>()
    val text_repeat_period : ObservableField<String> = ObservableField<String>()

    val hour : ObservableField<String> = ObservableField<String>()
    val minute : ObservableField<String> = ObservableField<String>()

    val amClick : ObservableField<Boolean> = ObservableField<Boolean>(true)
    val pmClick : ObservableField<Boolean> = ObservableField<Boolean>(false)

    val nextEnable : ObservableField<Boolean> = ObservableField<Boolean>(false)

    private val _nextEvent = MutableLiveData<Event<Alarm>>()
    val nextEvent : LiveData<Event<Alarm>>
        get() = _nextEvent

    fun onTitleChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        text_title.set(s.toString())

        var isValidTitle = !TextUtils.isEmpty(text_title.get())
        var isValidPeriod = !TextUtils.isEmpty(text_repeat_period.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidTitle && isValidPeriod && isValidSelected && isValidHour && isValidMinute
        validation(isValid)
    }

    fun onRepeatPeriodChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        text_repeat_period.set(s.toString())

        var isValidTitle = !TextUtils.isEmpty(text_title.get())
        var isValidPeriod = !TextUtils.isEmpty(text_repeat_period.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidTitle && isValidPeriod && isValidSelected && isValidHour && isValidMinute
        validation(isValid)
    }

    fun onHourChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        hour.set(s.toString())
        var isValidTitle = !TextUtils.isEmpty(text_title.get())
        var isValidPeriod = !TextUtils.isEmpty(text_repeat_period.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidTitle && isValidPeriod && isValidSelected && isValidHour && isValidMinute
        validation(isValid)
    }

    fun onMinuteChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        minute.set(s.toString())
        var isValidTitle = !TextUtils.isEmpty(text_title.get())
        var isValidPeriod = !TextUtils.isEmpty(text_repeat_period.get())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.get())
        var isValidMinute = !TextUtils.isEmpty(minute.get())

        var isValid = isValidTitle && isValidPeriod && isValidSelected && isValidHour && isValidMinute
        validation(isValid)
    }

    fun validation(valid : Boolean) {
        nextEnable.set(valid)
    }

    fun onNextEvent() {
        _nextEvent.value = Event(Alarm(hour.get(), minute.get()))
    }
}