package com.alio.ulio.view.ui.main.alarmsend.viewmodel

import android.app.Application
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

    private val _nextEvent = MutableLiveData<Event<String>>()
    val nextEvent : LiveData<Event<String>>
        get() = _nextEvent

    fun onNextEvent() {
        _nextEvent.value = Event("ddd")
    }
}