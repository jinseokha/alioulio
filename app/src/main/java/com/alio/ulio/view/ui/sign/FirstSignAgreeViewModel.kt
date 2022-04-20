package com.alio.ulio.view.ui.sign

import android.app.Application
import android.widget.CompoundButton
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

    val nextEnable : ObservableField<Boolean> = ObservableField<Boolean>(false)

    var kakaoCheck : ObservableField<Boolean> = ObservableField<Boolean>(false)
    var serviceCheck : ObservableField<Boolean> = ObservableField<Boolean>(false)
    var personalInfoCheck : ObservableField<Boolean> = ObservableField<Boolean>(false)
    var ageCheck : ObservableField<Boolean> = ObservableField<Boolean>(false)

    private val _nextEvent = MutableLiveData<Event<Boolean>>()
    val nextEvent : LiveData<Event<Boolean>>
        get() = _nextEvent

    fun onNextEvent() {
        _nextEvent.value = Event(true)
    }

    fun onKakaoInfoChanged(buttonView: CompoundButton, isChecked: Boolean) {
        kakaoCheck.set(isChecked)

        var isValid = kakaoCheck.get()!! && serviceCheck.get()!! && personalInfoCheck.get()!! && ageCheck.get()!!
        validation(isValid)
    }

    fun onServiceChanged(buttonView: CompoundButton, isChecked: Boolean) {
        serviceCheck.set(isChecked)

        var isValid = kakaoCheck.get()!! && serviceCheck.get()!! && personalInfoCheck.get()!! && ageCheck.get()!!
        validation(isValid)
    }

    fun onPersonalChanged(buttonView: CompoundButton, isChecked: Boolean) {
        personalInfoCheck.set(isChecked)

        var isValid = kakaoCheck.get()!! && serviceCheck.get()!! && personalInfoCheck.get()!! && ageCheck.get()!!
        validation(isValid)
    }

    fun onAgeChanged(buttonView: CompoundButton, isChecked: Boolean) {
        ageCheck.set(isChecked)

        var isValid = kakaoCheck.get()!! && serviceCheck.get()!! && personalInfoCheck.get()!! && ageCheck.get()!!
        validation(isValid)
    }

    fun validation(valid : Boolean) {
        nextEnable.set(valid)
    }
}