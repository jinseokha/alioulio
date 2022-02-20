package com.alio.ulio.view.ui.main.alarmsend.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.util.Event
import kotlin.math.min

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

    private val _nextEnable = MutableLiveData<Boolean>(true)
    val nextEnable : LiveData<Boolean>
        get() = _nextEnable

    private val _nextEvent = MutableLiveData<Event<String>>()
    val nextEvent : LiveData<Event<String>>
        get() = _nextEvent

    init {
        text_date.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })

        amClick.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })

        pmClick.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })

        hour.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })

        minute.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })
    }

    fun validation() {
        var isValidDate = !TextUtils.isEmpty(text_date.toString())
        var isValidSelected = amClick.get()!! || pmClick.get()!!

        var isValidHour = !TextUtils.isEmpty(hour.toString())
        var isValidMinute = !TextUtils.isEmpty(minute.toString())

        var isValid = isValidDate && isValidSelected && isValidHour && isValidMinute

        _nextEnable.value = isValid
    }

    fun onNextEvent() {
        _nextEvent.value = Event("ddd")
    }

    fun onCalendarClick() {
        val temp = calendarVisible.get() ?: true
        calendarVisible.set(!temp)
    }

}