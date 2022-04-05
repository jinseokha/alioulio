package com.alio.ulio.view.ui.sign

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.db.entity.Alarm
import com.alio.ulio.util.Event

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-13
 * @desc
 */
class FirstSignAgreeViewModel(application : Application) : BaseViewModel(application) {

    val nextEnable : ObservableField<Boolean> = ObservableField<Boolean>(true)

    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent

    fun onNextEvent() {
        _nextEvent.value = Event(true)
    }
}