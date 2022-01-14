package com.alio.ulio

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-14
 * @desc
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //.init(this, "a44dbbde821cc4ee30bbd9bb029f6c6f")
        KakaoSdk.init(this, "appKey")
    }
}