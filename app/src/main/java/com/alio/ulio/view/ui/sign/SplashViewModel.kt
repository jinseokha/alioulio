package com.alio.ulio.view.ui.sign

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alio.ulio.base.BaseViewModel
import com.kakao.sdk.user.UserApiClient

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-26
 * @desc
 */
class SplashViewModel(application: Application) : BaseViewModel(application)  {


    private val _autoCheckLogin : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val autoCheckLogin : LiveData<Boolean>
        get() = _autoCheckLogin


    fun autoLoginCheck() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                _autoCheckLogin.value = false
            } else if (tokenInfo != null) {
                _autoCheckLogin.value = true

            }
        }
    }
}