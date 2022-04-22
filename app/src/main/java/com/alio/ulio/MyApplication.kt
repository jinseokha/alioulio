package com.alio.ulio

import android.app.Application
import com.alio.ulio.db.Preferences
import com.kakao.sdk.common.KakaoSdk

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-14
 * @desc
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "97b4bfe24482572fdafb0039db83b2cb")
        Preferences.init(this)
    }
}