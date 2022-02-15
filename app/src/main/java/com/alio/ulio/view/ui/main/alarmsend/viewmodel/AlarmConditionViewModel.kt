package com.alio.ulio.view.ui.main.alarmsend.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.util.Event

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

    private val _nextEnable = MutableLiveData<Boolean>(true)
    val nextEnable : LiveData<Boolean>
        get() = _nextEnable

    private val _nextEvent = MutableLiveData<Event<String>>()
    val nextEvent : LiveData<Event<String>>
        get() = _nextEvent

    fun onNextEvent() {
        _nextEvent.value = Event("ddd")
    }

    fun onCalendarClick() {
        val temp = calendarVisible.get() ?: true
        calendarVisible.set(!temp)
    }

}