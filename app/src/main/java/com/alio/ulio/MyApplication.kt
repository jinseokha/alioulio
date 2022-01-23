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

        KakaoSdk.init(this, "97b4bfe24482572fdafb0039db83b2cb")
    }
}