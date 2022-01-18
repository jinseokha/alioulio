package com.alio.ulio.view.ui.sign

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.alio.ulio.util.Event
import com.alio.ulio.util.SingleLiveEvent
import com.kakao.sdk.user.UserApiClient

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-13
 * @desc
 */
class SignViewModel(application: Application) : BaseViewModel(application) {

    private val _autoCheckLogin : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val autoCheckLogin : LiveData<Boolean>
        get() = _autoCheckLogin

    private val _kakaoLoginClick = SingleLiveEvent<Event<Boolean>>()
    val kakaoLoginClick : LiveData<Event<Boolean>>
        get() = _kakaoLoginClick

    private val _memberInfoRule = SingleLiveEvent<Event<Boolean>>()
    val memberInfoRule : LiveData<Event<Boolean>>
        get() = _memberInfoRule


    fun kakaoLoginClick() {
        _kakaoLoginClick.value = Event(true)
    }

    fun memberInfoRuleClick() {
        _memberInfoRule.value = Event(true)
    }


    fun autoLoginCheck() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                _autoCheckLogin.value = false
                //toast(getString(R.string.token_info_error))
            } else if (tokenInfo != null) {
                _autoCheckLogin.value = true
                /*toast(getString(R.string.token_info_success))
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()*/
            }
        }
    }
}